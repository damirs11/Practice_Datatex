package com.company.services;

import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * Data access class for DB
 */
public class DataBaseService {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:testdb;create=true";
    private static final File personInput = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("InputXML/InputPerson.xml")).getFile());


    private static Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    /**
     * Load a Apache derby driver and
     * create tables with data
     */
    public static void init() {
        try {
            Class.forName(DRIVER).newInstance();
            loadingData(Tables.PERSONS);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
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

    /**
     * Method which create tables
     *
     * @param tableName
     */
    private static void loadingData(Tables tableName) {
        try (Connection connection = getConnection()) {
            createTableWithData(connection, tableName);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    private static void createTableWithData(Connection connection, Tables tableName) throws SQLException {
        switch (tableName) {
            case PERSONS:
                if (tableExists(connection, tableName)) {
                    deleteTable(connection, tableName);
                }
                createTablePerson(connection);
                insertDataForPerson(connection);
                break;
        }
    }

    private static boolean tableExists(Connection connection, Tables tableName) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, tableName.getTable().toUpperCase(), null);

        return result.next();
    }

    private static boolean deleteTable(Connection connection, Tables tableName) throws SQLException {
        String query = "DROP TABLE " + Tables.PERSONS.getTable();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return preparedStatement.execute();
        }
    }

    private static boolean createTablePerson(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "CREATE TABLE " + Tables.PERSONS.getTable() +
                        "(id INT, departmentId INT, name VARCHAR(50), middleName VARCHAR(50), secondName VARCHAR(50), position VARCHAR(50))")) {
            return preparedStatement.execute();
        }
    }

    private static void insertDataForPerson(Connection connection) {
        try {
            String query = "INSERT INTO " + Tables.PERSONS +
                    "(id, departmentId, name, middleName, secondName, position) VALUES (?, ?, ?, ?, ?, ?)";

            Collection<Person> personList = JaxbParser.getObject(personInput, Person.class).getList();

            for (Person person : personList) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, person.getId());
                    preparedStatement.setInt(2, person.getDepartmentId());
                    preparedStatement.setString(3, person.getName());
                    preparedStatement.setString(4, person.getMiddleName());
                    preparedStatement.setString(5, person.getSecondName());
                    preparedStatement.setString(6, person.getPosition());

                    preparedStatement.execute();
                }
            }
        } catch (JAXBException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Make create to DB and return collection
     *
     * @return the persons
     */
    public static Collection<Person> getPersons() {
        Collection<Person> collectionOfPersons = new ArrayList();

        String query = "SELECT * FROM " + Tables.PERSONS;
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt(1));
                person.setDepartmentId(resultSet.getInt(2));
                person.setName(resultSet.getString(3));
                person.setMiddleName(resultSet.getString(4));
                person.setSecondName(resultSet.getString(5));
                person.setPosition(resultSet.getString(6));

                collectionOfPersons.add(person);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return collectionOfPersons;
    }

    /**
     * Return count of rows in @param tableName
     *
     * @param tableName the table name
     * @return the count of rows
     */
    public static Integer countOf(Tables tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName.getTable();
        try (Statement preparedStatement = getConnection().createStatement()) {
            try (ResultSet resultSet = preparedStatement.executeQuery(query)) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return -1;
    }
}
