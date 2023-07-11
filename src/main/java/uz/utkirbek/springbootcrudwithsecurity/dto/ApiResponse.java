package uz.utkirbek.springbootcrudwithsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Muallif: Utkirbek
 * Soat: 11:50:51
 * Kun: July 10, 2023, Monday
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
