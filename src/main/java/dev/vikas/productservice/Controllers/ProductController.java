package dev.vikas.productservice.Controllers;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.Services.ProductService;
import dev.vikas.productservice.dtos.Exceptiondto;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreproxyProductService") ProductService productService)
    {
        this.productService=productService;
    }
   @GetMapping("{id}")
   public GenericProductDto getProductById(@PathVariable("id") Long id) throws NotFoundException
   {
       return productService.getProductById(id);
   }

   @PostMapping
   public GenericProductDto createProduct(@RequestBody GenericProductDto product)
   {
       return productService.createProduct(product);
   }

   @GetMapping
   public List<GenericProductDto> getAllproducts()
   {
       return List.of(new GenericProductDto(),new GenericProductDto());
   }

   @DeleteMapping("{id}")
   public ResponseEntity<GenericProductDto> deleteProduct(@PathVariable Long id)
   {
       return new ResponseEntity<>(productService.deleteProduct(id), HttpStatus.OK);
   }

   @PutMapping("{id}")
   public GenericProductDto Updatebyid(@RequestBody GenericProductDto product ,@PathVariable Long id)
   {
       return productService.Updatebyid(product,id);
   }

}
