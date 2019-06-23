package services;

import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class DataBaseService {

    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    private static final String JDBC_URL = "jdbc:derby:testdb;create=true";

    private static Logger logger = LoggerFactory.getLogger(DataBaseService.class);

    public static void init() {
        try {
            Class.forName(DRIVER);
            loadingData(Tables.PERSONS);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

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
                if (!hasExistsTable(connection, tableName)) {
                    if (createTablePerson(connection)) {
                        insertDataForPerson(connection);
                    }
                } else {
                    insertDataForPerson(connection);
                }
                break;
            case DOCUMENTS:
                break;
            default:
                throw new IllegalStateException("Wrong table name");
        }
    }

    private static boolean hasExistsTable(Connection connection, Tables tableName) throws SQLException {

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, null, null);

        while (resultSet.next()) {
            if (resultSet.getString("TABLE_NAME").equals(tableName.getTable())) {
                return true;
            }
        }
        return false;
    }

    private static boolean createTablePerson(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "CREATE TABLE " + Tables.PERSONS.getTable() +
                        "(id INT, Name VARCHAR(50), middleName VARCHAR(50), secondName VARCHAR(50), position VARCHAR(50))")) {
            return preparedStatement.execute();
        }
    }

    private static void insertDataForPerson(Connection connection) {
        try {
            File personInput = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("InputXML/InputPerson.xml")).getFile());

            String query = "INSERT INTO " + Tables.PERSONS + " (id, firstName, middleName, secondName, position, departmentId) VALUES(?, ?, ?, ?, ?, ?)";

            for (Person person : JaxbParser.getObject(personInput, Person.class).getList()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, person.getId());
                    preparedStatement.setString(2, person.getName());
                    preparedStatement.setString(3, person.getMiddleName());
                    preparedStatement.setString(4, person.getSecondName());
                    preparedStatement.setString(5, person.getPosition());
                    preparedStatement.setInt(6, person.getDepartmentId());

                    preparedStatement.execute();
                }
            }
        } catch (JAXBException | SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static Collection getPersons() throws SQLException {
        Collection collectionOfPersons = new ArrayList();

        String query = "SELECT * FROM " + Tables.PERSONS;
        try (Statement statement = getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {

                }
            }
        }

        return collectionOfPersons;
    }
}
