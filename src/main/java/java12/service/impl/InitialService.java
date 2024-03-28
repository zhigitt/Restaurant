package java12.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import java12.entities.Category;
import java12.entities.Restaurant;
import java12.entities.User;
import java12.entities.enums.RestType;
import java12.entities.enums.Role;
import java12.repository.CategoryRepo;
import java12.repository.RestaurantRepo;
import java12.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InitialService {
    private final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepo categoryRepo;

    @PostConstruct @Transactional
     void saveAdmin() {
        User admin = new User(
                "Zhigit", "Turumbekov",
                LocalDate.of(2003, 1, 15),
                "zhigit@gmail.com",
                passwordEncoder.encode("zhigit"),
                "+99675555352",
                Role.ADMIN,
                6);
        userRepo.save(admin);

        Restaurant restaurant = new Restaurant();

        restaurant.setName("Sandyk");
        restaurant.setLocation("Naryn");
        restaurant.setRestType(RestType.CAFE);
        restaurant.setService(15);
        restaurant.setNumberOfEmployees(15);

        restaurantRepo.save(restaurant);
        restaurant.addUser(admin);
        restaurantRepo.save(restaurant);
    }

    @PostConstruct @Transactional
    void saveCategory(){
        Category tamak = new Category(
                "Tamak"
        );

        Category sushnyak = new Category(
                "Sushnyak"
        );

        Category desert = new Category(
                "Desert"
        );

        categoryRepo.save(tamak);
        categoryRepo.save(sushnyak);
        categoryRepo.save(desert);
    }
}
