import com.company.Models.Staff.Department;
import com.company.Models.Staff.Organization;
import com.company.Models.Staff.Person;
import com.company.Models.Staff.PersonsList;
import com.company.Parser.Impl.JaxbParser;
import com.company.Parser.Parser;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;

public class JaxbParserTest {

    private Parser parser;
    private File filePerson;
    private File fileOrganization;
    private File fileDepartment;

    @Before
    public void setUp() {
        parser = new JaxbParser();
        filePerson = new File(Person.class.getSimpleName() + ".xml");
        fileOrganization = new File(Organization.class.getSimpleName() + ".xml");
        fileDepartment = new File(Department.class.getSimpleName() + ".xml");
    }

    @Test
    public void testSaveObject() throws JAXBException {
        Person person = new Person(1);
        person.setName("Имя");
        person.setSecondName("Фамилия");
        person.setMiddleName("Отчество");
        person.setPosition("Должность");

        Person person2 = new Person(2);
        person2.setName("Имя");
        person2.setSecondName("Фамилия");
        person2.setMiddleName("Отчество");
        person2.setPosition("Должность");

        PersonsList personsList = new PersonsList();
        personsList.getPersonList().add(person);
        personsList.getPersonList().add(person2);

        Organization organization = new Organization(2);
        organization.setFullName("Полное имя");
        organization.setShortName("Короткое имя");
        organization.setPhoneNumber("+7 999 999 99 99");
        organization.setOrganizationHead("Глава организации");

        Department department = new Department(3);
        department.setFullName("Полное имя");
        department.setShortName("Короткое имя");
        department.setPhoneNumber("+7 999 999 99 99");
        department.setDepartmentHead("Глава");


        //parser.saveObject(filePerson, person);
        parser.saveObject(filePerson, personsList);
        parser.saveObject(fileOrganization, organization);
        parser.saveObject(fileDepartment, department);
    }

    @Test
    public void testGetObject() throws JAXBException {
        Person person = (Person) parser.getObject(filePerson, Person.class);
        System.out.println(person);

        Organization organization = (Organization) parser.getObject(fileOrganization, Organization.class);
        System.out.println(organization);

        Department department = (Department) parser.getObject(fileDepartment, Department.class);
        System.out.println(department);
    }

}
