package com.dembasiby.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private String id;
    private String title;
    private PriceDTO priceDTO;
    private String category;
    private String description;
    private String image;

}
