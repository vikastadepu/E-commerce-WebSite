package dev.vikas.productservice.Services;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.Repository.CategoryRepository;
import dev.vikas.productservice.Repository.ProductRepository;
import dev.vikas.productservice.dtos.CategoryDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Category;
import dev.vikas.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public CategoryDto convertCategoryToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    @Override
    public List<GenericProductDto> getProductsByACategory(String categoryName) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryName);
        List<GenericProductDto>  genericProductDtoList = new ArrayList<>();
        if(categoryOptional.isPresent()) {
            List<Product> products = categoryOptional.get().getProducts();

            for (Product product : products
            ) {
                GenericProductDto genericProductDto = convertProductIntoGenericProduct(product);
                genericProductDtoList.add(genericProductDto);
            }
        }
        return genericProductDtoList;
    }

    private GenericProductDto convertProductIntoGenericProduct(Product product ) {

        return getGenericProductDto(product);
    }

    static GenericProductDto getGenericProductDto(Product product) {
        GenericProductDto genericProductDto  = new GenericProductDto();
        genericProductDto.setId(product.getId());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice());
        genericProductDto.setCategory(product.getCategory().getName());
        return genericProductDto;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for (Category category: categories
        ) {
            categoryDtoList.add(convertCategoryToCategoryDto(category));
        }

        return categoryDtoList;
    }

}
