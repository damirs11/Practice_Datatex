package parser;

import com.company.models.staff.Department;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Organization;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JaxbParserTest {

    private Logger logger = LoggerFactory.getLogger(JaxbParserTest.class);

    private File filePerson;
    private File fileOrganization;
    private File fileDepartment;

    @Before
    public void setUp() {
        String rootInResources = "src/main/resources/InputXML/";
        filePerson = new File(rootInResources + "InputPerson.xml");
        fileOrganization = new File(rootInResources + "InputOrganization.xml");
        fileDepartment = new File(rootInResources + "InputDepartment.xml");
    }

    @Test
    public void testSaveObject() throws JAXBException {

        Person person1 = new Person(1, "", "", "", "", 1);
        Person person2 = new Person(2, "", "", "", "", 2);
        Person person3 = new Person(3, "", "", "", "", 3);

        ListWrapper<Person> personListWrapper = new ListWrapper<>();
        personListWrapper.getList().add(person1);
        personListWrapper.getList().add(person2);
        personListWrapper.getList().add(person3);
        JaxbParser.saveObject(filePerson, personListWrapper);

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("+79173628888");
        phoneNumbers.add("+78883628888");
        phoneNumbers.add("+78883828888");

        Organization organization1 = new Organization(1, "", "", 1, phoneNumbers);
        Organization organization2 = new Organization(2, "", "", 2, phoneNumbers);
        Organization organization3 = new Organization(3, "", "", 3, phoneNumbers);

        ListWrapper<Organization> organizationListWrapper = new ListWrapper<>();
        organizationListWrapper.getList().add(organization1);
        organizationListWrapper.getList().add(organization2);
        organizationListWrapper.getList().add(organization3);
        JaxbParser.saveObject(fileOrganization, organizationListWrapper);


        Department department1 = new Department(1, "", "", 1, phoneNumbers);
        Department department2 = new Department(2, "", "", 2, phoneNumbers);
        Department department3 = new Department(3, "", "", 3, phoneNumbers);

        ListWrapper<Department> departmentListWrapper = new ListWrapper<>();
        departmentListWrapper.getList().add(department1);
        departmentListWrapper.getList().add(department2);
        departmentListWrapper.getList().add(department3);
        JaxbParser.saveObject(fileDepartment, departmentListWrapper);
    }

    @Test
    public void testGetObject() throws JAXBException {
        ListWrapper<Person> personListWrapper = JaxbParser.getObject(filePerson, Person.class);
        for (Person person : personListWrapper.getList()) {
            logger.info(String.valueOf(person));
        }

        ListWrapper<Organization> organizationListWrapper = JaxbParser.getObject(fileOrganization, Organization.class);
        for (Organization organization : organizationListWrapper.getList()) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.valueOf(organization));
            }
        }

        ListWrapper<Department> departmentListWrapper = JaxbParser.getObject(fileDepartment, Department.class);
        for (Department department : departmentListWrapper.getList()) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.valueOf(department));
            }
        }
    }

}
