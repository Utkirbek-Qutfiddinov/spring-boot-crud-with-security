package uz.utkirbek.springbootcrudwithsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.utkirbek.springbootcrudwithsecurity.dto.ApiResponse;
import uz.utkirbek.springbootcrudwithsecurity.dto.ProductDto;
import uz.utkirbek.springbootcrudwithsecurity.entity.Product;
import uz.utkirbek.springbootcrudwithsecurity.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Muallif: Utkirbek
 * Soat: 08:46:45
 * Kun: July 12, 2023, Wednesday
 */

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public List<Product> getAll(){
        return repository.findAll();
    }

    public List<Product> getAll(int page, int size){
        return repository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Product getOne(Integer id){
        Optional<Product> optionalProduct=repository.findById(id);
        return optionalProduct.orElse(null);
    }

    public ApiResponse save(ProductDto productDto){
        if (repository.existsByName(productDto.getName()))
            return new ApiResponse("This product has been already added!", false, HttpStatus.CONFLICT);
        Product product=new Product();
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        repository.save(product);
        return new ApiResponse("New product has been succesfully added!", true, HttpStatus.OK);
    }

    public ApiResponse update(ProductDto productDto){
        if (productDto.getId()==null)
            return new ApiResponse("ID can not bu null!",false,HttpStatus.NOT_FOUND);

        if (repository.existsByNameAndIdNot(productDto.getName(), productDto.getId()))
            return new ApiResponse("There is another product with this name!", false, HttpStatus.CONFLICT);
        Product product=new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        repository.save(product);
        return new ApiResponse("Product has been succesfully edited!", true, HttpStatus.OK);
    }

    public ApiResponse delete(Integer id){
        if (id==null)
            return new ApiResponse("ID can not bu null!",false,HttpStatus.NOT_FOUND);

        if (!repository.existsById(id))
            return new ApiResponse("There is no product with this ID!",false,HttpStatus.NOT_FOUND);

        repository.deleteById(id);

        return new ApiResponse("Product has been deleted succesfully!",true,HttpStatus.NO_CONTENT);
    }


}
