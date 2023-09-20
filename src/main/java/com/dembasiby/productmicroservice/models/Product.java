package com.dembasiby.productmicroservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {
   private String title;
   private String description;
   private String image;

   @ManyToOne(cascade = {CascadeType.PERSIST})
   @JoinColumn(name = "category")
   private Category category;

   private double price; 
}
