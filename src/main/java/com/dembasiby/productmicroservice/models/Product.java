package com.dembasiby.productmicroservice.models;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
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

   @OneToOne
   @JoinColumn(name = "price_id")
   private Price price;

}
