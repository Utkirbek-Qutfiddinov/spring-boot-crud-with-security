package uz.utkirbek.springbootcrudwithsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.utkirbek.springbootcrudwithsecurity.entity.User;

/**
 * Author: utkirbek.
 * Time: 21:59:31
 * Date: July 04, 2023, Tuesday
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);

    boolean existsByPhoneNumber(String phoneNumber);
}
