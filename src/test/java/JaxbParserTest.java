import com.company.Models.Staff.*;
import com.company.Parser.Impl.JaxbParser;
import com.company.Parser.Parser;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class JaxbParserTest {

    private Parser parser;
    private URL file;
    private File filePerson;
    private File fileOrganization;
    private File fileDepartment;

    @Before
    public void setUp() throws URISyntaxException {
        parser = new JaxbParser();

        file = ClassLoader.getSystemClassLoader().getResource("InputXML/InputPerson.xml");
        assert file != null;
        filePerson = new File(file.getFile());

        file = ClassLoader.getSystemClassLoader().getResource("InputXML/InputOrganization.xml");
        assert file != null;
        fileOrganization = new File(file.getFile());

        file = ClassLoader.getSystemClassLoader().getResource("InputXML/InputDepartment.xml");
        assert file != null;
        fileDepartment = new File(file.getFile());
    }

    @Test
    public void testSaveObject() throws JAXBException {
    }

    @Test
    public void testGetObject() throws JAXBException {
        PersonListWrapper personListWrapper = (PersonListWrapper) parser.getObject(filePerson, PersonListWrapper.class);
        for(Person person: personListWrapper.getPersonList()){
            System.out.println(person);
        }

        OrganizationListWrapper organizationListWrapper = (OrganizationListWrapper) parser.getObject(fileOrganization, OrganizationListWrapper.class);
        for(Organization organization: organizationListWrapper.getOrganization()){
            System.out.println(organization);
        }

        DepartmentListWrapper departmentListWrapper = (DepartmentListWrapper) parser.getObject(fileDepartment, DepartmentListWrapper.class);
        for(Department department: departmentListWrapper.getDepartment()){
            System.out.println(department);
        }
    }

}
