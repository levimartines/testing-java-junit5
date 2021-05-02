package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VetControllerTest {

    VetController controller;
    SpecialityMapService specialityService;
    VetMapService vetService;

    @BeforeEach
    void setUp() {
        specialityService = new SpecialityMapService();
        vetService = new VetMapService(specialityService);
        controller = new VetController(vetService);

        Vet vet = new Vet(1L, "TestVet", "One", new HashSet<>());
        Vet vet2 = new Vet(2L, "TestTwo", "Two", new HashSet<>());
        vetService.save(vet);
        vetService.save(vet2);
    }

    @Test
    @DisplayName("Test proper view name is returned for listVets page")
    void listVets() {

        ModelMapImpl model = new ModelMapImpl();
        assertEquals("vets/index", controller.listVets(model), "Wrong view returned");
        Set vets = (Set) model.getMap().get("vets");
        assertEquals(2, vets.size());
    }
}
