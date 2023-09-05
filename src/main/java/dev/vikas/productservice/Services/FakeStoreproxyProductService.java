package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.FakeStoreProductDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import dev.vikas.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreproxyProductService")
public class FakeStoreproxyProductService implements ProductService{
    //to connect to the external Apis RestTemplateBuilder can be used
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequesturl="https://fakestoreapi.com/products/{id}";

    public FakeStoreproxyProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }
    public GenericProductDto getProductById(Long id)
    {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(getProductRequesturl, FakeStoreProductDto.class,id);
        //response
        FakeStoreProductDto fakeStoreProductDto= response.getBody();

        //Product product=new Product();

        GenericProductDto product= new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getDescription());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }

}
