package com.dembasiby.productmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProductStoreDTO {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;

}