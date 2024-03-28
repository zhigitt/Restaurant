package java12.service.impl;

import jakarta.transaction.Transactional;
import java12.dto.request.ChefRequest;
import java12.dto.request.SignInRequest;
import java12.dto.request.SignUpRequest;
import java12.dto.request.WaiterRequest;
import java12.dto.response.RegisterResponse;
import java12.dto.response.SignInResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.Restaurant;
import java12.entities.User;
import java12.entities.enums.Role;
import java12.config.jwt.JwtService;
import java12.exception.NotFoundException;
import java12.repository.RestaurantRepo;
import java12.repository.UserRepo;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j

public class UserImpl implements UserService {

    private  final UserRepo userRepo;
    private final RestaurantRepo restaurantRepo;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public RegisterResponse signUp(SignUpRequest signUpRequest) {
        boolean exists = userRepo.existsByEmail(signUpRequest.getEmail());
        if (exists) throw new RuntimeException("Email : " + signUpRequest.getEmail() + " already exist");

        User user = new User();
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setDateOfBirth(signUpRequest.getDateOfBirth());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRole(signUpRequest.getRole());
        user.setExperience(signUpRequest.getExperience());

        userRepo.save(user);
        String newToken = jwtService.createToken(user);
        log.info("User successfully saved!");
        return RegisterResponse.builder()
                .token(newToken)
                .simpleResponse(java12.dto.response.SimpleResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("qwertytrewertyujik")
                        .build())
                .build();
    }

    @Override
    public SignInResponse sigIn(SignInRequest signInRequest) {
        User user = userRepo.findByEmail(signInRequest.email()).orElseThrow(() ->
                new NotFoundException("User with email: " + signInRequest.email() + " not found!"));

        String encodePassword = user.getPassword();
        String password =signInRequest.password();

        boolean matches = passwordEncoder.matches(password, encodePassword);

        if (!matches) throw new RuntimeException("Invalid password");

        String token = jwtService.createToken(user);
        return SignInResponse.builder()
                .token(token)
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public SimpleResponse update(Principal principal, Long userID, User user) {
        return null;
    }

    @Override
    public User findById(Long userId) {
        return null;
    }

    @Override
    public SimpleResponse saveWaiter(Long restaurantId, WaiterRequest waiterRequest) {
        Restaurant restaurant1 = restaurantRepo.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant id invalid!"));

        User newUser = new User();

        newUser.setFirstName(waiterRequest.getFirstName());
        newUser.setLastName(waiterRequest.getLastName());
        newUser.setDateOfBirth(waiterRequest.getDateOfBirth());
        newUser.setEmail(waiterRequest.getEmail());
        newUser.setPhoneNumber(waiterRequest.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(waiterRequest.getPassword()));
        newUser.setExperience(waiterRequest.getExperience());
        newUser.setRole(Role.CHEF);

        restaurant1.getUsers().add(newUser);
        userRepo.save(newUser);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Waiter saved!")
                .build();
    }

    @Override
    @Transactional
    public SimpleResponse savePofar(Long restaurantId, ChefRequest chefRequest) {

        Restaurant restaurant1 = restaurantRepo.findById(restaurantId).orElseThrow(() ->
                new NotFoundException("Restaurant id invalid!"));

        User newUser = new User();


        newUser.setFirstName(chefRequest.getFirstName());
        newUser.setLastName(chefRequest.getLastName());
        newUser.setDateOfBirth(chefRequest.getDateOfBirth());
        newUser.setEmail(chefRequest.getEmail());
        newUser.setPhoneNumber(chefRequest.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(chefRequest.getPassword()));
        newUser.setExperience(chefRequest.getExperience());
        newUser.setRole(Role.CHEF);

        restaurant1.getUsers().add(newUser);
        userRepo.save(newUser);

        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Chef saved!")
                .build();
    }
}
