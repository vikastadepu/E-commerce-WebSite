package dev.vikas.productservice.Services;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.Repository.CategoryRepository;
import dev.vikas.productservice.Repository.ProductRepository;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Category;
import dev.vikas.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class SelfProductServiceImpl implements ProductService{

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private GenericProductDto convertProductIntoGenericProduct(Product product) {
        return getGenericProductDto(product);
    }

    static GenericProductDto getGenericProductDto(Product product) {
        GenericProductDto genericProductDto  = new GenericProductDto();
        //genericProductDto.setId(product.getId());
        genericProductDto.setImage(product.getImage());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setCategory(product.getCategory().getName());
        return genericProductDto;
    }



    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product not found");
        return convertProductIntoGenericProduct(product.get());
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto)
    {
        Product product  = new Product();
        setProductFromGenericProductDto(genericProductDto, product);

        productRepository.save(product);
        return convertProductIntoGenericProduct(product);
    }

    @Override
    public GenericProductDto Updatebyid(Long id, GenericProductDto genericProductDto) throws NotFoundException {

        Optional<Product>  product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product not found");

        Product updatedProduct = product.get();
        setProductFromGenericProductDto(genericProductDto, updatedProduct);

        productRepository.save(updatedProduct);
        return convertProductIntoGenericProduct(updatedProduct);
    }

    private void setProductFromGenericProductDto(GenericProductDto genericProductDto, Product product) {
        product.setImage(genericProductDto.getImage());
        product.setDescription(genericProductDto.getDescription());
        product.setTitle(genericProductDto.getTitle());
        product.setPrice(genericProductDto.getPrice());

        Optional<Category> categoryOptional = categoryRepository.findByName(genericProductDto.getCategory());

        if(categoryOptional.isPresent()){
            product.setCategory(categoryOptional.get());
        }
        else{
            Category category = new Category();
            category.setName(genericProductDto.getCategory());
            product.setCategory(category);
        }
    }

    @Override
    public GenericProductDto deleteProduct(Long id) throws NotFoundException {
        Optional<Product>  product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product not found");
        productRepository.deleteById(id);
        return convertProductIntoGenericProduct(product.get());
    }

    @Override
    public List<GenericProductDto> getAllProducts()
    {
        List<Product>  products = productRepository.findAll();
        List<GenericProductDto>  genericProductDtoList = new ArrayList<>();

        for (Product product: products) {
            GenericProductDto genericProductDto = convertProductIntoGenericProduct(product);
            genericProductDtoList.add(genericProductDto);
        }
        return genericProductDtoList;
    }



}
