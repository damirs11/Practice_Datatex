package com.company.services;

import com.company.annotation.Column;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Data access class for DB
 */
public class DataBaseService {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:testdb;create=true";
    private static final File personInput = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("InputXML/InputPerson.xml")).getFile());


    private static final Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    /**
     * Load a Apache derby driver and
     * create tables with data
     */
    public static void init() {
        try {
            Class.forName(DRIVER).newInstance();
            loadingData(Person.class, JaxbParser.getObject(personInput, Person.class).getList());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * @return current connection
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    private static <T> void loadingData(Class<T> clazz, Collection<T> collection) {
        try (Connection connection = getConnection()) {
            System.out.println("Loading Data");
            if (tableExists(connection, clazz)) {
                System.out.println("table exists!!!!!!!!!!");
                deleteTable(connection, clazz);
            }
            createTable(connection, clazz);
            insertData(connection, clazz, collection);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Query template: "CREATE TABLE tableName (columnName Type, columnName Type, ...)
     *
     * @param connection current connection
     * @param clazz      target
     * @return success status
     * @throws SQLException
     */
    private static <T> boolean createTable(Connection connection, Class<T> clazz) throws SQLException {

        StringBuilder query = new StringBuilder();

        query.append("CREATE TABLE ").append(clazz.getSimpleName()).append(annotatedFields(clazz, true));

        System.out.println(query.toString());

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            return preparedStatement.execute();
        }
    }

    /**
     * Query template: "INSERT INTO tableName (columnName, columnName, ...) VALUES (?, ?, ...)
     *
     * @param connection
     * @param clazz
     * @param collection
     * @throws SQLException
     */
    private static <T> void insertData(Connection connection, Class<T> clazz, Collection<T> collection) throws SQLException {
        System.out.println("Insert Data");
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO ").append(clazz.getSimpleName()).append(annotatedFields(clazz, false))
                .append("VALUES ").append(questionMarksForInsert(clazz));

        System.out.println(query);

        for (T obj : collection) {
            inputDataInPreparedStatement(connection, query.toString(), obj);
        }
    }

    private static <T> boolean deleteTable(Connection connection, Class<T> clazz) throws SQLException {
        String query = "DROP TABLE " + clazz.getSimpleName();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.execute();
        }
    }

    private static <T> boolean tableExists(Connection connection, Class<T> clazz) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, clazz.getSimpleName().toUpperCase(), null);
        return result.next();
    }

    /**
     * Make create to DB and return collection
     *
     * @return the persons
     */
    public static Collection<Person> getPersons() {
        Collection<Person> collectionOfPersons = new ArrayList();

        String query = "SELECT * FROM " + Person.class.getSimpleName();
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Person person = new Person();
                person.setName(resultSet.getString(1));
                person.setPosition(resultSet.getString(2));
                person.setMiddleName(resultSet.getString(3));
                person.setSecondName(resultSet.getString(4));
                person.setDepartmentId(resultSet.getInt(5));
                person.setId(resultSet.getInt(6));

                collectionOfPersons.add(person);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return collectionOfPersons;
    }

    public static <T> Integer countOf(Class<T> clazz) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) FROM ").append(clazz.getSimpleName());

        try (Statement preparedStatement = getConnection().createStatement()) {
            try (ResultSet resultSet = preparedStatement.executeQuery(query.toString())) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return -1;
    }

    private static String typeConverter(String javaType) {
        switch (javaType.toUpperCase()) {
            case "STRING":
                return "VARCHAR(50)";
        }
        return javaType;
    }

    private static <T> String annotatedFields(Class<T> clazz, boolean typesInputEnable) {
        StringBuilder string = new StringBuilder();

        string.append(" (");
        List<Field> fields = ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if (field.isAnnotationPresent(Column.class)) {

                if (field.getAnnotation(Column.class).value().equals("")) {
                    string.append(field.getName());
                } else {
                    string.append(field.getAnnotation(Column.class).value());
                }

                if (typesInputEnable) {
                    string.append(" ").append(typeConverter(field.getType().getSimpleName()));
                }

                if (i == fields.size() - 1) {
                } else {
                    string.append(", ");
                }

            }
        }
        string.append(") ");

        return string.toString();
    }

    private static String questionMarksForInsert(Class clazz) {
        StringBuilder string = new StringBuilder();

        string.append(" (");
        List<Field> fields = ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz);
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            if (field.isAnnotationPresent(Column.class)) {
                if (i == fields.size() - 1) {
                    string.append("?");
                } else {
                    string.append("?, ");
                }
            }
        }
        string.append(")");

        return string.toString();
    }

    private static <T> void inputDataInPreparedStatement(Connection connection, String query, T obj) {
        int parameterNumber = 1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Method method : obj.getClass().getMethods()) {
                if (method.getName().startsWith("get")) {
                    System.out.println(method.getName() + " " + method.invoke(obj));
                    switch (method.getReturnType().getSimpleName()) {
                        case "Integer":
                        case "int":
                            preparedStatement.setInt(parameterNumber++, (Integer) method.invoke(obj));
                            break;
                        case "String":
                            preparedStatement.setString(parameterNumber++, (String) method.invoke(obj));
                    }
                }
            }
            preparedStatement.execute();
        } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
