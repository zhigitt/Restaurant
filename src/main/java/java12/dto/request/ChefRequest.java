package java12.dto.request;

import java12.entities.enums.Role;
import java12.validation.PasswordValidation;
import java12.validation.PhoneNumberValidation;
import java12.validation.chef.ChefExpirense;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class ChefRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;
    @PasswordValidation
    private String password;
    @PhoneNumberValidation
    private String phoneNumber;
    @ChefExpirense
    private int experience;
}
