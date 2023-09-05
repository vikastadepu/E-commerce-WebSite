package dev.vikas.productservice.dtos;

import dev.vikas.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

//Dtos are the objects that are sent or received from outside
@Getter
@Setter
public class FakeStoreProductDto {
    private double price;
    private String title;
    private Long id;
    private String category;
    private String description;
    private  String image;
}
