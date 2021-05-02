package guru.springframework.sfgpetclinic.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1L, "Levi", "Ferreira");
        owner.setCity("Sorocaba");
        owner.setAddress("Test Address, 999");
        owner.setTelephone("555-5555");

        assertAll("Properties teste",
            () -> assertAll("Person properties",
                () -> assertEquals("Levi", owner.getFirstName(), "First name didn't match"),
                () -> assertEquals("Ferreira", owner.getLastName(), "Last name didn't match")),
            () -> assertAll("Owner properties",
                () -> assertEquals("Test Address, 999", owner.getAddress(), "Address didn't match"),
                () -> assertEquals("555-5555", owner.getTelephone(), "Telephone didn't match"))
            );
    }
}
