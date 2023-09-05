package dev.vikas.productservice.Controllers;

import dev.vikas.productservice.Services.ProductService;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/")
public class ProductController
{

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreproxyProductService") ProductService productService)
    {
        this.productService=productService;
    }
   @GetMapping("{id}")
   public GenericProductDto getProductById(@PathVariable("id") Long id)
   {
       return productService.getProductById(id);
   }

}
