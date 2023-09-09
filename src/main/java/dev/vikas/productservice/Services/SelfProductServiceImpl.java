package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;

import java.util.List;

public class SelfProductServiceImpl implements ProductService{
    @Override
    public GenericProductDto Updatebyid(Long id) {
        return null;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        return new GenericProductDto();
    }

    public GenericProductDto createProduct(GenericProductDto product){return new GenericProductDto();}

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        return null;
    }


}
