package guru.springframework.sfgpetclinic.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.services.OwnerService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class OwnerControllerTest implements ControllerTest {

  @Mock
  OwnerService service;

  @InjectMocks
  OwnerController controller;

  @Mock
  BindingResult result;


  @Test
  void processCreationFormHasNoErrors() {
    // given
    Owner owner = new Owner(2L, "Test", "Test");
    given(service.save(any())).willReturn(owner);
    given(result.hasErrors()).willReturn(false);

    // when
    String view = controller.processCreationForm(owner, result);

    // then
    assertEquals("redirect:/owners/2", view);
    then(service).should(times(1)).save(any());
  }

  @Test
  void processCreationFormHasErrors() {
    // given
    Owner owner = new Owner(1L, "Test", "Test");
    given(result.hasErrors()).willReturn(true);

    // when
    String view = controller.processCreationForm(owner, result);

    // then
    assertEquals("owners/createOrUpdateOwnerForm", view);
    then(service).should(times(0)).save(any());
  }

}