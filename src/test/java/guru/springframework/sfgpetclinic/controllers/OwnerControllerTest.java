package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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

  @Captor
  ArgumentCaptor<String> captor;

  private void setUpFindAllByLastNameLike() {
    given(service.findAllByLastNameLike(captor.capture()))
      .willAnswer(invocation -> {
        List<Owner> owners = new ArrayList<>();

        String name = invocation.getArgument(0);

        switch (name) {
          case "%Buck%":
            owners.add(new Owner(1L, "Joe", "Buck"));
            return owners;
          case "%DontFindMe%":
            return owners;
          case "%FindMe%":
            owners.add(new Owner(1L, "Joe", "Buck"));
            owners.add(new Owner(2L, "Joe2", "Buck2"));
            return owners;
        }

        throw new RuntimeException("Invalid Argument");
      });
  }

  @Test
  void processFindFormWildcardFound() {
    setUpFindAllByLastNameLike();
    //given
    Owner owner = new Owner(1L, "Joe", "FindMe");

    //when
    String viewName = controller.processFindForm(owner, result, Mockito.mock(Model.class));

    //then
    assertThat("%FindMe%").isEqualToIgnoringCase(captor.getValue());
    assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
  }

  @Test
  void processFindFormWildcardStringAnnotation() {
    setUpFindAllByLastNameLike();

    //given
    Owner owner = new Owner(1L, "Joe", "Buck");

    //when
    String viewName = controller.processFindForm(owner, result, null);

    //then
    assertThat("%Buck%").isEqualToIgnoringCase(captor.getValue());
    assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
  }

  @Test
  void processFindFormWildcardNotFound() {
    setUpFindAllByLastNameLike();

    //given
    Owner owner = new Owner(1L, "Joe", "DontFindMe");

    //when
    String viewName = controller.processFindForm(owner, result, null);

    //then
    assertThat("%DontFindMe%").isEqualToIgnoringCase(captor.getValue());
    assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
  }

  @Test
  void processCreationFormHasErrors() {
    //given
    Owner owner = new Owner(1L, "Jim", "Bob");
    given(result.hasErrors()).willReturn(true);

    //when
    String viewName = controller.processCreationForm(owner, result);

    //then
    assertThat(viewName).isEqualToIgnoringCase("owners/createOrUpdateOwnerForm");
  }

  @Test
  void processCreationFormNoErrors() {
    //given
    Owner owner = new Owner(5L, "Jim", "Bob");
    given(result.hasErrors()).willReturn(false);
    given(service.save(any())).willReturn(owner);

    //when
    String viewName = controller.processCreationForm(owner, result);

    //then
    assertThat(viewName).isEqualToIgnoringCase("redirect:/owners/5");
  }

}