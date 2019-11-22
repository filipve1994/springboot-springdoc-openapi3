package com.filip.examples.springbootspringdocopenapi3;

import com.filip.examples.springbootspringdocopenapi3.models.Category;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import com.filip.examples.springbootspringdocopenapi3.models.Tag;
import com.filip.examples.springbootspringdocopenapi3.repositories.CategoryRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.PetRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class StartUpInit implements CommandLineRunner {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public void run(String... args) throws Exception {

        Category dogs = new Category();
        dogs.setId(1L);
        dogs.setName("Dogs");

        categoryRepository.save(dogs);

        Category cats = new Category();
        cats.setId(2L);
        cats.setName("Cats");
        categoryRepository.save(cats);

        Category rabbits = new Category();
        rabbits.setId(3L);
        rabbits.setName("Rabbits");
        categoryRepository.save(rabbits);

        Category lions = new Category();
        lions.setId(4L);
        lions.setName("Lions");
        categoryRepository.save(lions);

        Tag tag1 = new Tag();
        tag1.setName("tag1");
        tagRepository.save(tag1);

        Tag tag2 = new Tag();
        tag2.setName("tag2");
        tagRepository.save(tag2);

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setCategory(cats);
        pet.setName("Cat 1");
        pet.setPhotoUrls(Arrays.asList("url1", "url2"));
        pet.setTags(Arrays.asList(tag1, tag2));
        pet.setStatus(Pet.StatusEnum.AVAILABLE);

        petRepository.save(pet);

//        petRepository.save(createPet(1, cats, "Cat 1", new String[] { "url1", "url2" }, new String[] { "tag1", "tag2" },
//                Pet.StatusEnum.AVAILABLE));
//        petRepository.save(createPet(2, cats, "Cat 2", new String[] { "url1", "url2" }, new String[] { "tag2", "tag3" },
//                Pet.StatusEnum.AVAILABLE));
//        petRepository.save(createPet(3, cats, "Cat 3", new String[] { "url1", "url2" }, new String[] { "tag3", "tag4" },
//                Pet.StatusEnum.PENDING));
//
//        petRepository.save(createPet(4, dogs, "Dog 1", new String[] { "url1", "url2" }, new String[] { "tag1", "tag2" },
//                Pet.StatusEnum.AVAILABLE));
//        petRepository.save(createPet(5, dogs, "Dog 2", new String[] { "url1", "url2" }, new String[] { "tag2", "tag3" },
//                Pet.StatusEnum.SOLD));
//        petRepository.save(createPet(6, dogs, "Dog 3", new String[] { "url1", "url2" }, new String[] { "tag3", "tag4" },
//                Pet.StatusEnum.PENDING));
//
//        petRepository.save(createPet(7, lions, "Lion 1", new String[] { "url1", "url2" },
//                new String[] { "tag1", "tag2" }, Pet.StatusEnum.AVAILABLE));
//        petRepository.save(createPet(8, lions, "Lion 2", new String[] { "url1", "url2" },
//                new String[] { "tag2", "tag3" }, Pet.StatusEnum.AVAILABLE));
//        petRepository.save(createPet(9, lions, "Lion 3", new String[] { "url1", "url2" },
//                new String[] { "tag3", "tag4" }, Pet.StatusEnum.AVAILABLE));
//
//        petRepository.save(createPet(10, rabbits, "Rabbit 1", new String[] { "url1", "url2" },
//                new String[] { "tag3", "tag4" }, Pet.StatusEnum.AVAILABLE));


    }

    private static Pet createPet(long id, Category category, String name, String[] urls, String[] tags,
                                 Pet.StatusEnum status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setCategory(category);
        pet.setName(name);
        pet.setStatus(status);

        if (null != urls) {
            pet.setPhotoUrls(Arrays.asList(urls));
        }

        final AtomicLong i = new AtomicLong(0);
        if (null != tags && tags.length > 0) {
            Arrays.stream(tags).map(tag -> {
                Tag tagN = new Tag();
                tagN.setName(tag);
                tagN.setId(i.incrementAndGet());
                return tagN;
            }).forEach(pet::addTagsItem);
        }
        return pet;
    }
}
