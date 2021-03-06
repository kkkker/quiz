package com.twuc.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
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
    @Size(max = 50)
    private String units;

    @NotNull
    @Max(100000000)
    private Double price;

    @NotEmpty
    @Size(max = 200)
    private  String imageUrl;
}
