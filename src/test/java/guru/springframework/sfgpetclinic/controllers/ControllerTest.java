package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("controller")
public interface ControllerTest {

    @BeforeAll
    default void beforeAll() {
        System.out.println("BeforeAll Controller Test");
    }

}
