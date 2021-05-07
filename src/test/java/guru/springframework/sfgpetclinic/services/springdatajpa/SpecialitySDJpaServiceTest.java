package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
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
    void findById() {
        Speciality speciality = new Speciality("dogs");
        when(repository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpeciality = service.findById(1L);
        Assertions.assertEquals("dogs", foundSpeciality.getDescription());
    }

    @Test
    void delete() {
        Speciality speciality = new Speciality("dogs");
        service.delete(speciality);

        verify(repository).delete(any(Speciality.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);
        verify(repository, times(2)).deleteById(anyLong());
        verify(repository, never()).deleteById(2L);
    }
}
