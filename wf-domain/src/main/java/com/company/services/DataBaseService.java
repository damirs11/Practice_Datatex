package com.company.services;

import com.company.annotation.Column;
import com.company.annotation.Table;
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

    private DataBaseService() {
    }

    /**
     * Load a Apache derby driver and
     * create tables with data
     */
    public static void init() {
        try {
            Class.forName(DRIVER).newInstance();
            load(Person.class, JaxbParser.getObject(personInput, Person.class).getList());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | JAXBException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Query template: "SELECT COUNT(*) FROM tableName"
     *
     * @return count of fields
     */
    public static <T> Integer countOf(Class<T> clazz) {
        StringBuilder query = new StringBuilder().append("SELECT COUNT(*) FROM ").append(getTableName(clazz));

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

    /**
     * EntryPoint for query
     *
     */
    private static <T> void load(Class<T> clazz, Collection<T> collection) {
        try (Connection connection = getConnection()) {
            if (getTableExists(connection, clazz)) {
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
     */
    private static <T> void createTable(Connection connection, Class<T> clazz) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(getTableName(clazz)).append(getAnnotatedFields(clazz, true));

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            preparedStatement.execute();
        }
    }

    /**
     * Query template: "SELECT field, field, ... FROM tableName
     */
    public static <T> Collection<T> readTable(Class<T> clazz) {
        StringBuilder query = new StringBuilder().append("SELECT * FROM ").append(getTableName(clazz));
        Collection<T> collection = new ArrayList<>();

        try (ResultSet resultSet = getConnection().prepareStatement(query.toString()).executeQuery()) {
            while (resultSet.next()) {
                int parameterNumber = 1;
                T obj = clazz.newInstance();
                for (Method method : obj.getClass().getMethods()) {
                    if (method.getName().startsWith("set") && parameterNumber <= resultSet.getMetaData().getColumnCount()) {
                        switch (resultSet.getObject(parameterNumber).getClass().getSimpleName()) {
                            case "Integer":
                            case "int":
                                method.invoke(obj, resultSet.getInt(parameterNumber++));
                                break;
                            case "String":
                                method.invoke(obj, resultSet.getString(parameterNumber++));
                        }
                    }
                }
                collection.add(obj);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | SQLException e) {
            logger.error(e.getMessage());
        }
        return collection;
    }

    /**
     * Query template: "DROP TABLE tableName
     */
    private static <T> boolean deleteTable(Connection connection, Class<T> clazz) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE " + getTableName(clazz))) {
            return preparedStatement.execute();
        }
    }

    /**
     * Query template: "INSERT INTO tableName (columnName, columnName, ...) VALUES (?, ?, ...)
     *
     */
    private static <T> void insertData(Connection connection, Class<T> clazz, Collection<T> collection) {
        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO ").append(getTableName(clazz)).append(getAnnotatedFields(clazz, false))
                .append("VALUES ").append(getQuestionMarksForInsert(clazz));

        for (T obj : collection) {
            inputDataInPreparedStatement(connection, query.toString(), obj);
        }
    }

    private static <T> void inputDataInPreparedStatement(Connection connection, String query, T obj) {
        int parameterNumber = 1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Method method : obj.getClass().getMethods()) {
                if (method.getName().startsWith("get")) {
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

    /**
     * @return current connection
     * @throws SQLException
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    private static String getDerbyType(String javaType) {
        switch (javaType.toUpperCase()) {
            case "STRING":
                return "VARCHAR(50)";
            default:
                return javaType;
        }

    }

    private static String getQuestionMarksForInsert(Class clazz) {
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

    private static <T> String getTableName(Class<T> clazz) {
        String tableName = null;
        if (clazz.isAnnotationPresent(Table.class)) {
            if (clazz.getAnnotation(Table.class).value().equals("")) {
                tableName = clazz.getSimpleName().toUpperCase();
            } else {
                tableName = clazz.getAnnotation(Table.class).value().toUpperCase();
            }
        }
        return tableName;
    }

    private static <T> String getAnnotatedFields(Class<T> clazz, boolean typesInputEnable) {
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
                    string.append(" ").append(getDerbyType(field.getType().getSimpleName()));
                }

                if (i != fields.size() - 1) {
                    string.append(", ");
                }

            }
        }
        string.append(") ");

        return string.toString();
    }

    private static <T> boolean getTableExists(Connection connection, Class<T> clazz) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, getTableName(clazz), null);
        return result.next();
    }
}
