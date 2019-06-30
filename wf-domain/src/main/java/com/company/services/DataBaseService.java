package com.company.services;

import com.company.models.staff.Department;
import com.company.models.staff.Organization;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.utils.AnnotationUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Data access class for DB
 */
public class DataBaseService {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:testdb;create=true";
    private static final File personInput = new File(Objects.requireNonNull(DataBaseService.class.getClassLoader().getResource("InputXML/InputPerson.xml")).getFile());
    private static final File departmentInput = new File(Objects.requireNonNull(DataBaseService.class.getClassLoader().getResource("InputXML/InputDepartment.xml")).getFile());
    private static final File organizationInput = new File(Objects.requireNonNull(DataBaseService.class.getClassLoader().getResource("InputXML/InputOrganization.xml")).getFile());
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
            load(Department.class, JaxbParser.getObject(departmentInput, Department.class).getList());
            load(Organization.class, JaxbParser.getObject(organizationInput, Organization.class).getList());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | JAXBException e) {
            logger.error("Error while try init {}: {}", DataBaseService.class, e.getMessage());
        }
    }

    /**
     * Count of entities in DB
     *
     * @param clazz the POJO
     * @return count of entities
     */
    public static <T> Integer countOf(Class<T> clazz) {
        String query = String.format(COUNT_OF_QUERY_TEMPLATE, AnnotationUtils.getTableName(clazz));
        logger.info(query);
        try (Connection connection = getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            logger.error("Error while try SELECT all columns from table {}", e.getMessage());
        }
        return -1;
    }


    /**
     * Delete table if exists and create table with {@param collection}
     *
     * @param clazz target
     * @param collection of data
     */
    private static <T> void load(Class<T> clazz, Collection<T> collection) {
        try (Connection connection = getConnection()) {
            if (AnnotationUtils.getTableExists(connection, clazz)) {
                deleteTable(clazz);
            }
            createTable(clazz);
            insertData(clazz, collection);
        } catch (SQLException e) {
            logger.error("Error while try load table {}", e.getMessage());
        }
    }

    /**
     * Create table with taken name from @Table annotation
     *
     * @param clazz target
     * @throws SQLException
     */
    private static <T> void createTable(Class<T> clazz) throws SQLException {
        String query = String.format(CREATE_TABLE_QUERY_TEMPLATE,
                AnnotationUtils.getTableName(clazz),
                AnnotationUtils.getAnnotatedColumnFields(clazz, true));
        logger.info(query);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        }
    }

    /**
     * Select all data from {@param clazz} table
     *
     * @param clazz the clazz
     * @return collection of {@param <T>}
     */
    public static <T> Collection<T> readTable(Class<T> clazz) {
        String query = String.format(READ_TABLE_QUERY_TEMPLATE,
                AnnotationUtils.getAnnotatedColumnFields(clazz, false),
                AnnotationUtils.getTableName(clazz));
        logger.info(query);
        Collection<T> collection = new ArrayList<>();

        try (Connection connection = getConnection(); ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next()) {
                int parameterNumber = 1;
                T obj = clazz.newInstance();

                for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(obj.getClass())) {
                    String simpleName = resultSet.getObject(parameterNumber).getClass().getSimpleName();
                    if (Integer.class.getSimpleName().equals(simpleName)) {
                        PropertyUtils.setSimpleProperty(obj, field.getName(), resultSet.getInt(parameterNumber++));
                    } else if (String.class.getSimpleName().equals(simpleName)) {
                        PropertyUtils.setSimpleProperty(obj, field.getName(), resultSet.getString(parameterNumber++));
                    } else {
                        throw new IllegalStateException("Unexpected value: " + resultSet.getObject(parameterNumber).getClass().getSimpleName());
                    }
                }
                collection.add(obj);
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | SQLException | NoSuchMethodException e) {
            logger.error("Error while try read table {}", e.getMessage());
        }
        return collection;
    }

    /**
     * Delete table
     *
     * @param clazz target
     * @return status
     * @throws SQLException
     */
    private static <T> boolean deleteTable(Class<T> clazz) throws SQLException {
        String query = String.format(DROP_TABLE_QUERY_TEMPLATE, AnnotationUtils.getTableName(clazz));
        logger.info(query);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.execute();
        }
    }

    /**
     * Insert collection to table
     *
     * @param clazz      target
     * @param collection for input
     */
    private static <T> void insertData(Class<T> clazz, Collection<T> collection) {
        String query = String.format(INSERT_INTO_QUERY_TEMPLATE,
                AnnotationUtils.getTableName(clazz),
                AnnotationUtils.getAnnotatedColumnFields(clazz, false),
                AnnotationUtils.getQuestionMarksForInsert(clazz));
        logger.info(query);
        for (T obj : collection) {
            try (Connection connection = getConnection()) {
                inputDataInPreparedStatement(connection, query, obj);
            } catch (SQLException e) {
                logger.error("Error while try input data in prepared statement {}", e.getMessage());
            }
        }
    }

    /**
     * Take a {@param query} and input specify data in it
     *
     * @param query Prepared Statement with ?
     * @param obj to insert
     */
    private static <T> void inputDataInPreparedStatement(Connection connection, String query, T obj) {
        logger.info("{} - {}", query, obj);
        int parameterNumber = 1;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(obj.getClass())) {
                String typeName = field.getType().getSimpleName();
                if (Integer.class.getSimpleName().equals(typeName)) {
                    preparedStatement.setInt(parameterNumber++, (Integer) PropertyUtils.getProperty(obj, field.getName()));
                } else if (String.class.getSimpleName().equals(typeName)) {
                    preparedStatement.setString(parameterNumber++, (String) PropertyUtils.getProperty(obj, field.getName()));
                } else {
                    throw new IllegalStateException("Unexpected value: " + field.getType());
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
}
