package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class PersonTest implements ModelTest {

    @Test
    void groupedAssertions() {
        Person person = new Person(1L, "Levi", "Ferreira");

        assertAll("Test Props Set",
            () -> assertEquals("Levi", person.getFirstName(), "First name failed"),
            () -> assertEquals("Ferreira", person.getLastName(), "Last name failed"));
    }

    @RepeatedTest(value = 10, name = "{displayName} : {currentRepetition} of {totalRepetitions}")
    @DisplayName("Repeated Test")
    void myRepeatedTest() {
        System.out.println("My repeated test");
    }

}
