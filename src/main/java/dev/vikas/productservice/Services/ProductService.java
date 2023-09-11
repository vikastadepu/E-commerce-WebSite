package dev.vikas.productservice.Services;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;

import java.util.List;

public interface ProductService {

    GenericProductDto Updatebyid(GenericProductDto product,Long id);

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);

    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProduct(Long id);
}
