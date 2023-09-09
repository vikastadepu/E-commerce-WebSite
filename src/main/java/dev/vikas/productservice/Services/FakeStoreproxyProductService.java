package dev.vikas.productservice.Services;

import dev.vikas.productservice.dtos.FakeStoreProductDto;
import dev.vikas.productservice.dtos.GenericProductDto;
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
    //to connect to the external Apis RestTemplateBuilder can be used
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequesturl="https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";

    private String ProductRequestBaseurl="https://fakestoreapi.com/products";
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

//CreateProduct Api
    public GenericProductDto createProduct(GenericProductDto product)
    {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, GenericProductDto.class
        );
        return response.getBody();
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(ProductRequestBaseurl,FakeStoreProductDto[].class);

        List<GenericProductDto> answer= new ArrayList<>();

        for(FakeStoreProductDto fakeStoreProductDto: response.getBody())
        {
            GenericProductDto product= new GenericProductDto();
            product.setImage(fakeStoreProductDto.getImage());
            product.setTitle(fakeStoreProductDto.getDescription());
            product.setDescription(fakeStoreProductDto.getTitle());
            product.setPrice(fakeStoreProductDto.getPrice());
            product.setCategory(fakeStoreProductDto.getCategory());
            answer.add(product);
        }
        return answer;
    }


    public GenericProductDto deleteProduct(Long id) {

        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(getProductRequesturl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto product= new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getDescription());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());

        return product;
    }



    }


    //


