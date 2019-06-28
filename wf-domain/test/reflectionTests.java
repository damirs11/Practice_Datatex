import com.company.models.staff.Person;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class reflectionTests {

    @Test
    public void getMethodsTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (Field field : ReflectionUtils.getDeclaredFieldsIncludingInherited(Person.class)) {
            String name = field.getName();

            Person person = new Person();

            PropertyUtils.setSimpleProperty(person, name, "1");
            System.out.println(person);
        }

    }

}
