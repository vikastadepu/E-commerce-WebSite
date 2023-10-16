package dev.vikas.productservice.Controllers;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.Services.ProductService;
import dev.vikas.productservice.Services.SelfProductServiceImpl;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products-db")
public class ProductControllerDB {

    @Autowired
    private ProductService productService;

    public ProductControllerDB(ProductService productService)
    {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<GenericProductDto>> getAllProducts() throws NotFoundException {
        List<GenericProductDto> productDtoList = productService.getAllProducts();
        if (productDtoList.isEmpty()) {
            return new ResponseEntity<>(
                    productDtoList,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
}

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(
                productService.deleteProduct(id),
                HttpStatus.OK
        );
    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto genericProductDto) {
        return productService.createProduct(genericProductDto);
    }

    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody GenericProductDto genericProductDto) throws NotFoundException {
        return productService.Updatebyid(id, genericProductDto);
    }
}
