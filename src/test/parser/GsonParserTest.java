package parser;

import com.company.models.staff.Department;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Organization;
import com.company.models.staff.Person;
import com.company.parser.GsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonParserTest {

    private Logger logger = LoggerFactory.getLogger(GsonParser.class);

    private File filePerson;
    private File fileOrganization;
    private File fileDepartment;

    @Before
    public void setUp() {
        String rootInResources = "src/main/resources/OutputJson/";
        filePerson = new File(rootInResources + "OutputPerson.json");
        fileOrganization = new File(rootInResources + "OutputOrganization.json");
        fileDepartment = new File(rootInResources + "OutputDepartment.json");
    }

    @Test
    public void testSaveObject() throws IOException {

        Person person1 = new Person(1, "", "", "", "", 1);
        Person person2 = new Person(2, "", "", "", "", 2);
        Person person3 = new Person(3, "", "", "", "", 3);

        ListWrapper<Person> personListWrapper = new ListWrapper<>();
        personListWrapper.getList().add(person1);
        personListWrapper.getList().add(person2);
        personListWrapper.getList().add(person3);
        GsonParser.saveObject(filePerson, personListWrapper);

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
        GsonParser.saveObject(fileOrganization, organizationListWrapper);


        Department department1 = new Department(1, "", "", 1, phoneNumbers);
        Department department2 = new Department(2, "", "", 2, phoneNumbers);
        Department department3 = new Department(3, "", "", 3, phoneNumbers);

        ListWrapper<Department> departmentListWrapper = new ListWrapper<>();
        departmentListWrapper.getList().add(department1);
        departmentListWrapper.getList().add(department2);
        departmentListWrapper.getList().add(department3);
        GsonParser.saveObject(fileDepartment, departmentListWrapper);

    }

    @Test
    public void testGetObject() throws IOException {

        Type type = new TypeToken<ListWrapper<Person>>() {
        }.getType();
        ListWrapper<Person> personListWrapper = (ListWrapper<Person>) GsonParser.getObject(filePerson, type);
        for (Person person : personListWrapper.getList()) {
            if(logger.isDebugEnabled()) {
                logger.debug(String.valueOf(person));
            }

        }

        type = new TypeToken<ListWrapper<Organization>>() {
        }.getType();
        ListWrapper<Organization> organizationListWrapper = (ListWrapper<Organization>) GsonParser.getObject(filePerson, type);
        for (Organization organization : organizationListWrapper.getList()) {
            if(logger.isDebugEnabled()) {
                logger.debug(String.valueOf(organization));
            }
        }

        type = new TypeToken<ListWrapper<Department>>() {
        }.getType();
        ListWrapper<Department> departmentListWrapper = (ListWrapper<Department>) GsonParser.getObject(filePerson, type);
        for (Department department : departmentListWrapper.getList()) {
            if(logger.isDebugEnabled()) {
                logger.debug(String.valueOf(department));
            }
        }
    }

}
