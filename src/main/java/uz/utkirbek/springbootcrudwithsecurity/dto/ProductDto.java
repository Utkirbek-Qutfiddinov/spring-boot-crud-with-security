package uz.utkirbek.springbootcrudwithsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

/**
 * Muallif: Utkirbek
 * Soat: 08:53:56
 * Kun: July 12, 2023, Wednesday
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer id;

    @NotNull(message = "name can not be null")
    private String name;

    @NotNull(message = "quantity can not be null")
    private Integer quantity;
}
