package dev.vikas.productservice.thirdpartyclients.productservice.fakestore;

import dev.vikas.productservice.Exceptions.NotFoundException;
import dev.vikas.productservice.dtos.FakeStoreProductDto;
import dev.vikas.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Wrapper over Fakestore API

@Service
public class FakeStoreProductServiceClient{

    //to connect to the external Apis RestTemplateBuilder can be used
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestBaseurl="https://fakestoreapi.com/products/{id}";

    private String ProductRequestBaseurl="https://fakestoreapi.com/products";
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }


    public FakeStoreProductDto Updatebyid(GenericProductDto product, Long id) {

        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(getProductRequestBaseurl, HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(getProductRequestBaseurl, FakeStoreProductDto.class,id);
        //response
        FakeStoreProductDto fakeStoreProductDto= response.getBody();

        //Product product=new Product();
        if(fakeStoreProductDto==null)
        {
            throw new NotFoundException("Product with id:"+id+ "doesn't exist");
        }
        return fakeStoreProductDto;
    }

    //CreateProduct Api
    public FakeStoreProductDto createProduct(GenericProductDto product)
    {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                ProductRequestBaseurl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }


    public List<FakeStoreProductDto> getAllProducts() {

        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response=restTemplate.getForEntity(ProductRequestBaseurl,FakeStoreProductDto[].class);

        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDto deleteProduct(Long id) {

        RestTemplate restTemplate=restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(getProductRequestBaseurl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return fakeStoreProductDto;

    }
}
