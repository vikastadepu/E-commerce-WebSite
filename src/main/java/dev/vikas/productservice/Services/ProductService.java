package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;

public interface ProductService {

    GenericProductDto getProductById(Long id);
}
