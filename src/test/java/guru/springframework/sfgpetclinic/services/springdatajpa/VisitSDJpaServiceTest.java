package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
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
        given(repository.findAll()).willReturn(Arrays.asList(new Visit(), new Visit()));

        Set<Visit> visits = service.findAll();

        assertEquals(2, visits.size());
        then(repository).should().findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit(1L, LocalDate.now());
        given(repository.findById(1L)).willReturn(Optional.of(visit));

        Visit foundVisit = service.findById(1L);

        assertEquals(1L, foundVisit.getId());
        then(repository).should().findById(anyLong());
    }

    @Test
    void save() {
        Visit visit = new Visit(null, LocalDate.now());

        service.save(visit);
        then(repository).should().save(any(Visit.class));
    }

    @Test
    void delete() {
        Visit visit = new Visit(1L, LocalDate.now());
        service.delete(visit);

        then(repository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        then(repository).should().deleteById(anyLong());
    }
}
