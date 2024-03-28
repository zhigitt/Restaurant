package java12.dto.response;

import java12.entities.enums.Role;
import lombok.Builder;

@Builder
public record SignInResponse(
        String token,
        Long id,
        String email,
        Role role
) {
}
