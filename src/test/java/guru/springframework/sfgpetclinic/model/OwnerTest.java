package guru.springframework.sfgpetclinic.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import guru.springframework.sfgpetclinic.model.enums.OwnerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class OwnerTest implements ModelTest {

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
        assertThat(owner.getCity(), is("Sorocaba"));
    }

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @ValueSource(strings = {"Parametrized", "Test"})
    void testValueSource(String valueSource) {
        System.out.println(valueSource);
    }

    @DisplayName("ENUM Test")
    @ParameterizedTest(name = "{displayName} - [{index}] {argumentsWithNames}")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }
}
