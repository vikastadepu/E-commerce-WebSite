package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;

public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return new GenericProductDto();
    }
}
