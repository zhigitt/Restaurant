package java12.api;

import jakarta.validation.Valid;
import java12.dto.request.ChefRequest;
import java12.dto.request.WaiterRequest;
import java12.dto.response.SimpleResponse;
import java12.repository.RestaurantRepo;
import java12.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")

public class UserAPI {
    private final UserService userService;
    private final RestaurantRepo restaurantRepo;

    @Secured("ADMIN")
    @PostMapping("/{restaurantId}")
    public SimpleResponse chef(@PathVariable Long restaurantId,
                               @RequestBody @Valid ChefRequest chefRequest){
        return userService.savePofar(restaurantId, chefRequest);
    }

    @Secured("ADMIN")
    @PostMapping("/saveWaiter/{restaurantId}")
    public SimpleResponse waiter(@PathVariable Long restaurantId,
                                 @RequestBody @Valid WaiterRequest waiterRequest){
        return userService.saveWaiter(restaurantId, waiterRequest);
    }
}
