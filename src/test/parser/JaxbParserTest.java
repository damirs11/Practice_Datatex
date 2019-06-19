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
import java.util.Collection;

public class JaxbParserTest {

    private Logger logger = LoggerFactory.getLogger(JaxbParserTest.class);

    private File filePerson;
    private File fileOrganization;
    private File fileDepartment;
    private Collection<String> phoneNumbers;

    @Before
    public void setUp() {
        String rootInResources = "src/main/resources/InputXML/";
        filePerson = new File(rootInResources + "InputPerson.xml");
        fileOrganization = new File(rootInResources + "InputOrganization.xml");
        fileDepartment = new File(rootInResources + "InputDepartment.xml");

        phoneNumbers = new ArrayList<>();
        phoneNumbers.add("+79173626897");
        phoneNumbers.add("+79128156825");
        phoneNumbers.add("+79257812444");
    }

    @Test
    public void testPersonListSave() throws JAXBException {

        ListWrapper<Person> personListWrapper = new ListWrapper<>();

        for (int i = 1; i < 10; i++) {
            Person person = new Person(i, "Фамилия", "Имя", "Отчество", "Должность", i);
            personListWrapper.getList().add(person);
        }
        JaxbParser.saveObject(filePerson, personListWrapper);
    }

    @Test
    public void testOrganizationListSave() throws JAXBException {

        ListWrapper<Organization> organizationListWrapper = new ListWrapper<>();

        for (int i = 1; i < 10; i++) {
            Organization organization = new Organization(i, "Полное название", "Короткое название", i, phoneNumbers);
            organizationListWrapper.getList().add(organization);
        }
        JaxbParser.saveObject(fileOrganization, organizationListWrapper);
    }

    @Test
    public void testDepartmentListSave() throws JAXBException {

        ListWrapper<Department> departmentListWrapper = new ListWrapper<>();

        for (int i = 1; i < 10; i++) {
            Department department = new Department(i, "Полное название", "Короткое название", i, phoneNumbers, i);
            departmentListWrapper.getList().add(department);
        }
        JaxbParser.saveObject(fileDepartment, departmentListWrapper);
    }

    @Test
    public void testPersonListPrint() throws JAXBException {
        ListWrapper<Person> personListWrapper = JaxbParser.getObject(filePerson, Person.class);
        for (Person person : personListWrapper.getList()) {
            logger.info(String.valueOf(person));
        }
    }

    @Test
    public void testOrganizationListPrint() throws JAXBException {
        ListWrapper<Organization> organizationListWrapper = JaxbParser.getObject(fileOrganization, Organization.class);
        for (Organization organization : organizationListWrapper.getList()) {
            logger.info(String.valueOf(organization));
        }
    }

    @Test
    public void testDepartmentListPrint() throws JAXBException {
        ListWrapper<Department> departmentListWrapper = JaxbParser.getObject(fileDepartment, Department.class);
        for (Department department : departmentListWrapper.getList()) {
            logger.info(String.valueOf(department));
        }
    }

}


