package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class PersonTest implements ModelTest {

    @Test
    void groupedAssertions() {
        Person person = new Person(1L, "Levi", "Ferreira");

        assertAll("Test Props Set",
            () -> assertEquals("Levi", person.getFirstName(), "First name failed"),
            () -> assertEquals("Ferreira", person.getLastName(), "Last name failed"));
    }

    @RepeatedTest(value = 10, name = "{displayName}")
    @DisplayName("Repeated Test")
    void myRepeatedTestWithDI(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ": " + repetitionInfo.getCurrentRepetition());
    }

}
