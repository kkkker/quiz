package com.twuc.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotEmpty
    @Size(max = 50)
    private String name;

    @NotEmpty
    private String units;

    @NotNull
    private Double price;

    @NotEmpty
    private  String imageUrl;
}
