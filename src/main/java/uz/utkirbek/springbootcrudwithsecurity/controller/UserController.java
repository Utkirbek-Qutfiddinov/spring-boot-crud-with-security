package uz.utkirbek.springbootcrudwithsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.utkirbek.springbootcrudwithsecurity.dto.ApiResponse;
import uz.utkirbek.springbootcrudwithsecurity.dto.UserDto;
import uz.utkirbek.springbootcrudwithsecurity.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: utkirbek.
 * Time: 21:41:58
 * Date: July 04, 2023, Tuesday
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserDto user){
        ApiResponse apiResponse = userService.save(user);
       return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody UserDto user){
        ApiResponse apiResponse = userService.update(user);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
