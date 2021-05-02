package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import guru.springframework.sfgpetclinic.controllers.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    }

    @Test
    @DisplayName("Test oops handler")
    void oopsHandler() {
        assertThrows(NotFoundException.class, () -> controller.oopsHandler());
    }
}
