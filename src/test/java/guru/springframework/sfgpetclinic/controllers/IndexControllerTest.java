package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import guru.springframework.sfgpetclinic.controllers.exceptions.NotFoundException;
import java.time.Duration;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

class IndexControllerTest {

    IndexController controller;

    @BeforeEach
    void setUp() {
        controller = new IndexController();
    }

    @Test
    @DisplayName("Test Proper view name is returned for index page")
    void index() {
        assertEquals("index", controller.index(), "Wrong view returned");

        assertThat(controller.index()).isEqualTo("index");
    }

    @Test
    @DisplayName("Test oops handler")
    void oopsHandler() {
        assertThrows(NotFoundException.class, () -> controller.oopsHandler());
    }

    @Test
    @Disabled("assertTimeout example")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> {
            Thread.sleep(1000);
            System.out.println("I got here after 1sec");
        });
    }

    @Test
    @Disabled("Assumption example")
    void testAssumptionIsTrue() {
        Assumptions.assumeTrue("".equalsIgnoreCase(controller.index()));
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @Disabled("EnabledOnOs example")
    void testOnMacOS() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    @Disabled("EnabledOnJre example")
    void testOnJava11() {
    }

    @Test
    @EnabledIfEnvironmentVariable(named = "USER", matches = "ADMIN")
    @Disabled("EnabledIfEnvironmentVariable example")
    void testIfUserAdmin() {
    }
}
