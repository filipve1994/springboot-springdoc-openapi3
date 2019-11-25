package com.filip.examples.springbootspringdocopenapi3;

import com.filip.examples.springbootspringdocopenapi3.models.Category;
import com.filip.examples.springbootspringdocopenapi3.models.Order;
import com.filip.examples.springbootspringdocopenapi3.models.Pet;
import com.filip.examples.springbootspringdocopenapi3.models.Tag;
import com.filip.examples.springbootspringdocopenapi3.models.User;
import com.filip.examples.springbootspringdocopenapi3.repositories.CategoryRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.OrderRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.PetRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.TagRepository;
import com.filip.examples.springbootspringdocopenapi3.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class StartUpInit implements CommandLineRunner {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        createSamplePets();

        createSampleOrders();

        createSampleUsers();

    }

    private static Pet createPet2(long id, Category category,
                                  String name,
                                  List<String> urls,
                                  List<Tag> tags,
                                  Pet.StatusEnum status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setCategory(category);
        pet.setName(name);
        pet.setStatus(status);

        if (null != urls) {
            pet.setPhotoUrls(urls);
        }

        if (null != tags && tags.size() > 0) {
            pet.setTags(tags);
        }

        return pet;
    }

    private void createSamplePets() {

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

        Tag tag3 = new Tag();
        tag3.setName("tag3");
        tagRepository.save(tag3);

        Tag tag4 = new Tag();
        tag4.setName("tag4");
        tagRepository.save(tag4);

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setCategory(cats);
        pet.setName("Cat 1");
        pet.setPhotoUrls(Arrays.asList("url1", "url2"));
        pet.setTags(Arrays.asList(tag1, tag2));
        pet.setStatus(Pet.StatusEnum.AVAILABLE);

        petRepository.save(pet);

        petRepository.save(createPet2(2, cats, "Cat 2", Arrays.asList("url1", "url2"),
                Arrays.asList(tag2, tag3), Pet.StatusEnum.AVAILABLE));


        petRepository.save(createPet2(3, cats, "Cat 3", Arrays.asList("url1", "url2"), Arrays.asList(tag3, tag4),
                Pet.StatusEnum.PENDING));

        petRepository.save(createPet2(4, dogs, "Dog 1", Arrays.asList("url1", "url2"), Arrays.asList(tag1, tag2),
                Pet.StatusEnum.AVAILABLE));
        petRepository.save(createPet2(5, dogs, "Dog 2", Arrays.asList("url1", "url2"), Arrays.asList(tag2, tag3),
                Pet.StatusEnum.SOLD));
        petRepository.save(createPet2(6, dogs, "Dog 3", Arrays.asList("url1", "url2"), Arrays.asList(tag3, tag4),
                Pet.StatusEnum.PENDING));

        petRepository.save(createPet2(7, lions, "Lion 1", Arrays.asList("url1", "url2"),
                Arrays.asList(tag1, tag2), Pet.StatusEnum.AVAILABLE));
        petRepository.save(createPet2(8, lions, "Lion 2", Arrays.asList("url1", "url2"),
                Arrays.asList(tag2, tag3), Pet.StatusEnum.AVAILABLE));
        petRepository.save(createPet2(9, lions, "Lion 3", Arrays.asList("url1", "url2"),
        Arrays.asList(tag3, tag4), Pet.StatusEnum.AVAILABLE));

        petRepository.save(createPet2(10, rabbits, "Rabbit 1", Arrays.asList("url1", "url2"),
        Arrays.asList(tag3, tag4), Pet.StatusEnum.AVAILABLE));

    }

    private void createSampleOrders() {
        orderRepository.save(createOrder(1, 1, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(2, 1, Order.StatusEnum.DELIVERED));
        orderRepository.save(createOrder(3, 2, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(4, 2, Order.StatusEnum.DELIVERED));
        orderRepository.save(createOrder(5, 3, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(6, 3, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(7, 3, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(8, 3, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(9, 3, Order.StatusEnum.PLACED));
        orderRepository.save(createOrder(10, 3, Order.StatusEnum.PLACED));
    }

    private static Order createOrder(long id, long petId, Order.StatusEnum status) {

        Order order = new Order();
        order.setId(id);
        order.setPetId(petId);
        order.setQuantity(2);
        order.setShipDate(new Date());
        order.setStatus(status);

        return order;

    }

    private void createSampleUsers() {
        userRepository.save(createUser(1, "user1", "first name 1", "last name 1",
                "email1@test.com", 1));
        userRepository.save(createUser(2, "user2", "first name 2", "last name 2",
                "email2@test.com", 2));
        userRepository.save(createUser(3, "user3", "first name 3", "last name 3",
                "email3@test.com", 3));
        userRepository.save(createUser(4, "user4", "first name 4", "last name 4",
                "email4@test.com", 1));
        userRepository.save(createUser(5, "user5", "first name 5", "last name 5",
                "email5@test.com", 2));
        userRepository.save(createUser(6, "user6", "first name 6", "last name 6",
                "email6@test.com", 3));
        userRepository.save(createUser(7, "user7", "first name 7", "last name 7",
                "email7@test.com", 1));
        userRepository.save(createUser(8, "user8", "first name 8", "last name 8",
                "email8@test.com", 2));
        userRepository.save(createUser(9, "user9", "first name 9", "last name 9",
                "email9@test.com", 3));
        userRepository.save(createUser(10, "user10", "first name 10", "last name 10",
                "email10@test.com", 1));
        userRepository.save(createUser(11, "user?10", "first name ?10", "last name ?10",
                "email101@test.com", 1));
    }

    private static User createUser(long id, String username, String firstName, String lastName, String email, int userStatus) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword("XXXXXXXXXXX");
        user.setPhone("123-456-7890");
        user.setUserStatus(userStatus);

        return user;
    }
}
