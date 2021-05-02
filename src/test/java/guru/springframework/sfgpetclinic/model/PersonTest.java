package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("model")
class PersonTest {

    @Test
    void groupedAssertions() {
        Person person = new Person(1L, "Levi", "Ferreira");

        assertAll("Test Props Set",
            () -> assertEquals("Levi", person.getFirstName(), "First name failed"),
            () -> assertEquals("Ferreira", person.getLastName(), "Last name failed"));
    }

}
