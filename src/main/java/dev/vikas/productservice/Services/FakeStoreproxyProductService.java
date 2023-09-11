package dev.vikas.productservice.Services;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.dtos.FakeStoreProductDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreproxyProductService")
public class FakeStoreproxyProductService implements ProductService
{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreproxyProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient)
    {
        this.fakeStoreProductServiceClient=fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto Updatebyid(GenericProductDto product,Long id) {
        return convertfakeToGeneric(fakeStoreProductServiceClient.Updatebyid(product,id));
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {

        return convertfakeToGeneric(fakeStoreProductServiceClient.getProductById(id));
    }

//CreateProduct Api
    public GenericProductDto createProduct(GenericProductDto product)
    {
      return convertfakeToGeneric(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos=new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto:fakeStoreProductServiceClient.getAllProducts())
        {
            genericProductDtos.add(convertfakeToGeneric(fakeStoreProductDto));
        }
     return genericProductDtos;
    }


    public GenericProductDto deleteProduct(Long id) {
        return convertfakeToGeneric(fakeStoreProductServiceClient.deleteProduct(id));
    }

    private GenericProductDto convertfakeToGeneric(FakeStoreProductDto fakeStoreProductDto)
    {

        GenericProductDto product= new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getDescription());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }

    }





