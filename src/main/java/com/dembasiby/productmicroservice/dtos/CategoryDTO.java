package com.dembasiby.productmicroservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private String id;
    private String name;
    private List<GenericProductDTO> products;
}
