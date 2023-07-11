package uz.utkirbek.springbootcrudwithsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.utkirbek.springbootcrudwithsecurity.dto.ApiResponse;
import uz.utkirbek.springbootcrudwithsecurity.dto.UserDto;
import uz.utkirbek.springbootcrudwithsecurity.entity.User;
import uz.utkirbek.springbootcrudwithsecurity.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Muallif: Utkirbek
 * Soat: 08:32:08
 * Kun: July 10, 2023, Monday
 */

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getOne(Integer id){
        Optional<User> optionalUser=userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public ApiResponse save(UserDto userDto){
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new ApiResponse("Bunday User mavjud!",false, HttpStatus.CONFLICT);

        User user=new User();
        user.setAddress(userDto.getAddress());
        user.setFullName(userDto.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("User saqlandi",true,HttpStatus.CREATED);
    }

    public ApiResponse delete(Integer id){
        if(!userRepository.existsById(id))
            return new ApiResponse("User topilmadi",false, HttpStatus.BAD_REQUEST);

        userRepository.deleteById(id);
        return new ApiResponse("User o'chirildi!",true,HttpStatus.OK);

    }

    public ApiResponse update(UserDto userDto){
        if (userDto.getId()==null)
            return new ApiResponse("ID null bo'lmasligi kerak",false,HttpStatus.NOT_ACCEPTABLE);

        if (userRepository.existsByPhoneNumberAndIdNot(userDto.getPhoneNumber(), userDto.getId()))
            return new ApiResponse("Bunday foydalanuvchi mavjud!",false, HttpStatus.CONFLICT);

        Optional<User> optionalUser=userRepository.findById(userDto.getId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User topilmadi",false, HttpStatus.BAD_REQUEST);


        User user= optionalUser.get();
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setFullName(userDto.getFullName());
        user.setAddress(userDto.getAddress());

        userRepository.save(user);

        return new ApiResponse("User tahrirlandi!",true, HttpStatus.OK);

    }
}
