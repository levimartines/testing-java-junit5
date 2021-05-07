package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @Test
    void findAll() {

        Visit visit = new Visit();
        Visit visit2 = new Visit();
        Visit visit3 = new Visit();
        when(repository.findAll()).thenReturn(Arrays.asList(visit, visit2, visit3));

        Set<Visit> visits = service.findAll();
        Assertions.assertEquals(3, visits.size());

        verify(repository).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit(1L, LocalDate.now());
        when(repository.findById(1L)).thenReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);
        Assertions.assertEquals(1L, foundVisit.getId());

        verify(repository).findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit(null, LocalDate.now());

        service.save(visit);
        verify(repository).save(any());
    }

    @Test
    void delete() {
        Visit visit = new Visit(1L, LocalDate.now());
        service.delete(visit);

        verify(repository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }
}
