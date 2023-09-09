package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id);

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
}
