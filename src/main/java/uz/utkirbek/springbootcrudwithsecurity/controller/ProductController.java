package uz.utkirbek.springbootcrudwithsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.utkirbek.springbootcrudwithsecurity.dto.ApiResponse;
import uz.utkirbek.springbootcrudwithsecurity.dto.ProductDto;
import uz.utkirbek.springbootcrudwithsecurity.service.ProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Muallif: Utkirbek
 * Soat: 08:47:10
 * Kun: July 12, 2023, Wednesday
 */

@Controller
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping
    @PreAuthorize(value = "hasAnyAuthority('GET_PRODUCT')")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "size", required = false) Integer size){
        if (page==null || size==null)
            return ResponseEntity.ok(service.getAll());
        else return ResponseEntity.ok(service.getAll(page,size));
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('GET_PRODUCT')")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyAuthority('ADD_PRODUCT')")
    public ResponseEntity<?> save(@Valid @RequestBody ProductDto product){
        ApiResponse apiResponse = service.save(product);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasAnyAuthority('DELETE_PRODUCT')")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping
    @PreAuthorize(value = "hasAnyAuthority('EDIT_PRODUCT')")
    public ResponseEntity<?> update(@Valid @RequestBody ProductDto product){
        ApiResponse apiResponse = service.update(product);
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
