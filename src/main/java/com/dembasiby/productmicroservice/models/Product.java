package com.dembasiby.productmicroservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
   private String title;
   private String description;
   private Category category;
   private double price; 
   private String image;
}
