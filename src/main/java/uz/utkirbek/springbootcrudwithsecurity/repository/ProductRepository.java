package uz.utkirbek.springbootcrudwithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.utkirbek.springbootcrudwithsecurity.entity.Product;

import javax.validation.constraints.NotNull;

/**
 * Muallif: Utkirbek
 * Soat: 08:46:05
 * Kun: July 12, 2023, Wednesday
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
