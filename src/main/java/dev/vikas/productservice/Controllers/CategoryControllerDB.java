package dev.vikas.productservice.Controllers;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.Services.CategoryService;
import dev.vikas.productservice.dtos.CategoryDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController()
@RequestMapping("/categories")
public class CategoryControllerDB {

    private final CategoryService categoryService;

    public CategoryControllerDB(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        if (categoryDtoList.isEmpty()) {
            return new ResponseEntity<>(
                    categoryDtoList,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/name/{categoryName}")
    public ResponseEntity<List<GenericProductDto>> getProductsByACategory(@PathVariable("categoryName") String categoryName) throws NotFoundException {

        List<GenericProductDto> genericProductDtos = categoryService.getProductsByACategory(categoryName);
        if (genericProductDtos.isEmpty()) {
            return new ResponseEntity<>(
                    new ArrayList<>(),
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(genericProductDtos, HttpStatus.OK);
    }
}
