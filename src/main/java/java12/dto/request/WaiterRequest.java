package java12.dto.request;

import java12.entities.enums.Role;
import java12.validation.PasswordValidation;
import java12.validation.PhoneNumberValidation;
import java12.validation.waiter.WaiterAge;
import java12.validation.waiter.WaiterExpirense;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class WaiterRequest {
    private String firstName;
    private String lastName;
    @WaiterAge
    private LocalDate dateOfBirth;
    private String email;

    @PasswordValidation
    private String password;


    @PhoneNumberValidation
    private String phoneNumber;

    @WaiterExpirense
    private int experience;
}
