package ua.lviv.iot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;

    private String password;
}
