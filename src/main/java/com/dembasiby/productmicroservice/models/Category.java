package com.dembasiby.productmicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseModel {

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
