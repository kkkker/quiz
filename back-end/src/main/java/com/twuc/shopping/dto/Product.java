package com.twuc.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotEmpty
    private String name;
    private String units;
    private double price;
    private  String imageUrl;
}
