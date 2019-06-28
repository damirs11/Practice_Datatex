package com.company.services;

import com.company.annotation.Column;
import com.company.annotation.Table;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import org.apache.commons.beanutils.PropertyUtils;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data access class for DB
 */
public class DataBaseService {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:testdb;create=true";
    private static final File personInput = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("InputXML/InputPerson.xml")).getFile());
    private static final Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    private static final String CREATE_TABLE_QUERY_TEMPLATE = "CREATE TABLE %s (%s)";
    private static final String READ_TABLE_QUERY_TEMPLATE = "SELECT %s FROM %s";
    private static final String DROP_TABLE_QUERY_TEMPLATE = "DROP TABLE %s";
    private static final String INSERT_INTO_QUERY_TEMPLATE = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String COUNT_OF_QUERY_TEMPLATE = "SELECT COUNT(*) FROM %s";

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
            logger.error("Error while try init {}: {}", DataBaseService.class, e.getMessage());
        }
    }

    public static <T> Integer countOf(Class<T> clazz) {
        String query = String.format(COUNT_OF_QUERY_TEMPLATE, getTableName(clazz));
        try (Connection connection = getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error("Error while try SELECT all columns from table {}", e.getMessage());
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
            logger.error("Error while try load table {}", e.getMessage());
        }
    }

    private static <T> void createTable(Connection connection, Class<T> clazz) throws SQLException {
        String query = String.format(CREATE_TABLE_QUERY_TEMPLATE,
                getTableName(clazz), getAnnotatedFields(clazz, true));
        System.out.println(query);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        }
    }

    public static <T> Collection<T> readTable(Class<T> clazz) {
        String query = String.format(READ_TABLE_QUERY_TEMPLATE,
                getAnnotatedFields(clazz, false), getTableName(clazz));
        System.out.println(query);
        Collection<T> collection = new ArrayList<>();

        try (Connection connection = getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next()) {
                int parameterNumber = 1;
                T obj = clazz.newInstance();

                for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(obj.getClass())) {
                    switch (resultSet.getObject(parameterNumber).getClass().getSimpleName()) {
                        case "Integer":
                            PropertyUtils.setSimpleProperty(obj, field.getName(), resultSet.getInt(parameterNumber++));
                            break;
                        case "String":
                            PropertyUtils.setSimpleProperty(obj, field.getName(), resultSet.getString(parameterNumber++));
                            break;
                    }
                }
                collection.add(obj);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | SQLException | NoSuchMethodException e) {
            logger.error("Error while try read table {}", e.getMessage());
        }
        return collection;
    }

    private static <T> boolean deleteTable(Connection connection, Class<T> clazz) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                String.format(DROP_TABLE_QUERY_TEMPLATE, getTableName(clazz)))) {
            return preparedStatement.execute();
        }
    }

    private static <T> void insertData(Connection connection, Class<T> clazz, Collection<T> collection) {
        String query = String.format(INSERT_INTO_QUERY_TEMPLATE,
                getTableName(clazz), getAnnotatedFields(clazz, false), getQuestionMarksForInsert(clazz));
        for (T obj : collection) {
            inputDataInPreparedStatement(connection, query, obj);
        }
    }

    private static <T> void inputDataInPreparedStatement(Connection connection, String query, T obj) {
        int parameterNumber = 1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(obj.getClass())) {
                String name = field.getName();
                name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);

                Method method = obj.getClass().getMethod(name);

                switch (method.getReturnType().getSimpleName()) {
                    case "Integer":
                    case "int":
                        preparedStatement.setInt(parameterNumber++, (Integer) method.invoke(obj));
                        break;
                    case "String":
                        preparedStatement.setString(parameterNumber++, (String) method.invoke(obj));
                }
            }
            preparedStatement.execute();
        } catch (IllegalAccessException | InvocationTargetException | SQLException | NoSuchMethodException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * @return current connection
     * @throws SQLException SQLException
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
        return ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz)
                .stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> "?")
                .collect(Collectors.joining(", "));
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

    private static String getColumnName(Field annotatedField) {
        String string;
        if (annotatedField.getAnnotation(Column.class).value().equals("")) {
            string = annotatedField.getName();
        } else {
            string = annotatedField.getAnnotation(Column.class).value();
        }
        return string;
    }

    private static <T> String getAnnotatedFields(Class<T> clazz, boolean typesInputEnable) {
        return ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz).stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    if (typesInputEnable) {
                        return String.format("%s %s", DataBaseService.getColumnName(field), DataBaseService.getDerbyType(field.getType().getSimpleName()));
                    } else {
                        return String.format("%s", DataBaseService.getColumnName(field));
                    }
                })
                .collect(Collectors.joining(", "));
    }

    private static <T> boolean getTableExists(Connection connection, Class<T> clazz) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, getTableName(clazz), null);
        return result.next();
    }
}
