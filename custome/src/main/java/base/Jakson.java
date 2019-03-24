package base;

import base.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author dmz
 * @date Create in 21:10 2019/3/23
 */
public class Jakson {

    Person person = Person.builder().name("战神三").age("12").build();

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void test() throws Exception{
        String s = objectMapper.writeValueAsString(person);
        System.out.println(s);
    }
}
