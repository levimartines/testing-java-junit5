package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository repository;

    @InjectMocks
    SpecialitySDJpaService service;


    @Test
    void findAll() {
        // given
        given(repository.findAll())
            .willReturn(Arrays.asList(new Speciality("dogs"), new Speciality("cats")));

        // when
        Set<Speciality> specialities = service.findAll();

        // then
        assertEquals(2, specialities.size());
        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
    }


    @Test
    void findById() {
        Speciality speciality = new Speciality("dogs");
        given(repository.findById(1L)).willReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1L);

        assertEquals("dogs", foundSpeciality.getDescription());
        then(repository).should(times(1)).findById(anyLong());
    }

    @Test
    void delete() {
        Speciality speciality = new Speciality("dogs");
        service.delete(speciality);
        then(repository).should().delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        then(repository).should().deleteById(anyLong());
    }
}
