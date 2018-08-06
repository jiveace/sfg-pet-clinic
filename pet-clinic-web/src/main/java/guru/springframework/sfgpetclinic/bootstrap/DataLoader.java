package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        System.out.println("Loaded pet types...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Ken");
        owner1.setLastName("Patera");
        owner1.setAddress("123 Bricker Lane");
        owner1.setCity("Edinburgh");
        owner1.setTelephone("01314478469");

        Pet rover = new Pet();
        rover.setPetType(savedDogPetType);
        rover.setOwner(owner1);
        rover.setName("Rover");
        rover.setBirthDate(LocalDate.now());
        owner1.getPets().add(rover);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Tetsuya");
        owner2.setLastName("Naito");
        owner1.setAddress("123 Knife Crime Lane");
        owner1.setCity("Glasgow");
        owner1.setTelephone("01415568947");

        Pet teasel = new Pet();
        teasel.setPetType(savedCatPetType);
        teasel.setOwner(owner2);
        teasel.setName("Teasel");
        teasel.setBirthDate(LocalDate.now());
        owner2.getPets().add(teasel);

        ownerService.save(owner2);

        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Bobby");
        vet2.setLastName("Cleaver");

        vetService.save(vet2);

        System.out.println("Loaded vets...");



    }
}
