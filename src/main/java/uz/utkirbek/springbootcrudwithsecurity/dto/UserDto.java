package uz.utkirbek.springbootcrudwithsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Muallif: Utkirbek
 * Soat: 08:38:33
 * Kun: July 10, 2023, Monday
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull(message = "fullName can not be null")
    private String fullName;

    @NotNull(message = "phoneNumber can not be null")
    private String phoneNumber;

    @NotNull(message = "address can not be null")
    private String address;
}
