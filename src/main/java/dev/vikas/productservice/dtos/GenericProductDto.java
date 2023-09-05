package dev.vikas.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericProductDto {
    private double price;
    private String title;
    private Long id;
    private String category;
    private String description;
    private  String image;
}
