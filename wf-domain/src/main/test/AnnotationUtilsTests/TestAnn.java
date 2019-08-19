package AnnotationUtilsTests;

import com.company.models.staff.Person;
import com.company.utils.AnnotationUtils;
import org.junit.Test;

public class TestAnn {

    @Test
    public void getFiedsWithData() {
        Person person = new Person();
        person.setId(1);
        person.setFirstname("fs");
        person.setSurname("sn");
        person.setDepartmentId(1);
        person.setPatronymic("p");
        person.setPost("post");
        person.setPhoto("photo");

        System.out.println(AnnotationUtils.getAnnotatedColumnFieldsWithDataForUpdateQ(person));
    }
}
