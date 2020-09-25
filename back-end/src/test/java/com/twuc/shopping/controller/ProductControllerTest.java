package com.twuc.shopping.controller;

import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
        ProductEntity productEntity = ProductEntity.builder()
                .name("可乐")
                .price(3.5)
                .units("瓶")
                .imageUrl("可乐url")
                .build();
        productRepository.save(productEntity);

        productEntity = ProductEntity.builder()
                .name("面包")
                .price(3.0)
                .units("袋")
                .imageUrl("面包url")
                .build();
        productRepository.save(productEntity);
    }

    @Test
    void should_get_all_products() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",  hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("可乐")))
                .andExpect(jsonPath("$[0].price", is(3.5)))
                .andExpect(jsonPath("$[0].units", is("瓶")))
                .andExpect(jsonPath("$[0].imageUrl", is("可乐url")))
                .andExpect(jsonPath("$[1].name", is("面包")))
                .andExpect(jsonPath("$[1].price", is(3.0)))
                .andExpect(jsonPath("$[1].units", is("袋")))
                .andExpect(jsonPath("$[1].imageUrl", is("面包url")));
        List<ProductEntity> all = productRepository.findAll();
    }

}