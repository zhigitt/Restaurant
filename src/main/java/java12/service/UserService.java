package java12.service;

import java12.dto.request.ChefRequest;
import java12.dto.request.SignInRequest;
import java12.dto.request.SignUpRequest;
import java12.dto.request.WaiterRequest;
import java12.dto.response.HTTPResponse;
import java12.dto.response.RegisterResponse;
import java12.dto.response.SignInResponse;
import java12.dto.response.SimpleResponse;
import java12.entities.User;

import java.security.Principal;

public interface UserService {
    RegisterResponse signUp(SignUpRequest signUpRequest);

    SignInResponse sigIn(SignInRequest signInRequest);

    SimpleResponse update(Principal principal, Long userID, User user);

    User findById(Long userId);

    SimpleResponse saveWaiter(Long restaurantId, WaiterRequest waiterRequest);

    SimpleResponse savePofar(Long restaurantId, ChefRequest chefRequest);
}
