package pl.piomin.services.playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.piomin.services.playground.model.PersonsList;

@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    PersonsList personsList;

    @Test
    public void test() {
        Assertions.assertEquals(3, personsList.getPersons().size());
        Assertions.assertEquals("John", personsList.getPersons().get(0).getFirstName());
        Assertions.assertEquals("Tom", personsList.getPersons().get(1).getFirstName());
        Assertions.assertEquals("Kate", personsList.getPersons().get(2).getFirstName());
    }

}
