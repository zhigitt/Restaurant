package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.SignInRequest;
import java12.dto.request.SignUpRequest;
import java12.dto.response.RegisterResponse;
import java12.dto.response.SignInResponse;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthAPI {

    private final UserService userService;

    @PostMapping
    public RegisterResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public SignInResponse sigIn(@RequestBody SignInRequest signInRequest){
        return userService.sigIn(signInRequest);
    }

}

