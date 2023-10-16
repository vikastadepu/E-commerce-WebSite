package dev.vikas.productservice.Services;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.dtos.CategoryDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<GenericProductDto> getProductsByACategory(String categoryName) throws NotFoundException;

    List<CategoryDto> getAllCategories();
}
