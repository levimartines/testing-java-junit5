package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("service")
class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    PetTypeService petTypeService;
    PetService petService;

    @BeforeEach
    void setUp() {
        petTypeService = new PetTypeMapService();
        petService = new PetMapService();
        ownerMapService = new OwnerMapService(petTypeService, petService);

        System.out.println("BeforeEach OwnerMapServiceTest");
    }

    @DisplayName("Assert owners are zero")
    @Test
    void ownersAreZero() {
        int ownerCount = ownerMapService.findAll().size();
        assertEquals(0, ownerCount);

        System.out.println("ownersAreZero Test");
    }

    @DisplayName("PetType tests")
    @Nested
    class TestCreatePetTypes {

        @BeforeEach
        void setUp() {
            PetType petType = new PetType(1L, "Dog");
            PetType petType2 = new PetType(2L, "Cat");
            petTypeService.save(petType);
            petTypeService.save(petType2)
            ;
            System.out.println("BeforeEach TestCreatePetTypes");
        }

        @DisplayName("Test Pet count")
        @Test
        void testPetCount() {
            int petTypeCount = petTypeService.findAll().size();
            assertEquals(2, petTypeCount);

            System.out.println("testPetCount Test");
        }

        @DisplayName("Save Owners Tests")
        @Nested
        class SaveOwnersTests {

            @BeforeEach
            void setUp() {
                ownerMapService.save(new Owner(1L, "Before", "Each"));
                System.out.println("BeforeEach SaveOwnersTests");
            }

            @DisplayName("Save owner")
            @Test
            void saveOwner() {
                Owner owner = new Owner(2L, "Joe", "Buck");

                Owner savedOwner = ownerMapService.save(owner);
                assertNotNull(savedOwner);
                System.out.println("saveOwner Test");
            }

            @DisplayName("Save owners Tests")
            @Nested
            class FindOwnersTests {

                @DisplayName("Find owner")
                @Test
                void findOwner() {
                    Owner foundOwner = ownerMapService.findById(1L);
                    assertNotNull(foundOwner);
                    System.out.println("findOwner Test");
                }

                @DisplayName("Find owner not found")
                @Test
                void findOwnerNotFound() {
                    Owner foundOwner = ownerMapService.findById(2L);
                    assertNull(foundOwner);
                    System.out.println("findOwnerNotFound Test");
                }
            }
        }
    }


    @DisplayName("Verify still zero owners")
    @Test
    void ownersAreStillZero() {
        int ownerCount = ownerMapService.findAll().size();
        Assertions.assertEquals(0, ownerCount);

        System.out.println("ownersAreStillZero Test");
    }
}
