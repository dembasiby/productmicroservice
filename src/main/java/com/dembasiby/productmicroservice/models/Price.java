package com.dembasiby.productmicroservice.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Price extends BaseModel {
    // Price as a class to handle additional properties like currency
    // promotions, discounts, etc.
    private double price;
    private String currency;
}
