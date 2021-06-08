package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import guru.springframework.sfgpetclinic.services.map.PetMapService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VisitMapService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest implements ControllerTest {

    @InjectMocks
    VisitController controller;

    @Mock
    VisitService service;

    @Spy
    PetMapService petService;

    @Test
    void loadPetWithVisit() {
        // given
        Map<String, Object> model = new HashMap<>();
        Pet pet = new Pet(1L);
        petService.save(pet);
        given(petService.findById(anyLong())).willCallRealMethod();

        // when
        Visit visit = controller.loadPetWithVisit(1L, model);

        // then
        assertThat(visit).isNotNull();
        assertThat(visit.getPet()).isNotNull();
    }
}
