package com.twuc.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.dto.Product;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void should_get_all_products() throws Exception {
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

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
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

    @Test
    void should_add_product_success() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("success")));

        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
        assertEquals(product.getName(), productEntityList.get(0).getName());
        assertEquals(product.getImageUrl(), productEntityList.get(0).getImageUrl());
        assertEquals(product.getUnits(), productEntityList.get(0).getUnits());
        assertEquals(product.getPrice(), productEntityList.get(0).getPrice());
    }

    @Test
    void should_not_add_product_when_name_empty() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("")
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        product = Product.builder()
                .name(null)
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(0, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_name_length_more_than_50() throws Exception {

        String stringWith50Length = "12345678901234567890123456789012345678901234567890";

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name(stringWith50Length)
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        product = Product.builder()
                .name(stringWith50Length + "1")
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_price_empty() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(null)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(0, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_price_more_than_one_hundred_million() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("1234567890")
                .price(100000000.0)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        product = Product.builder()
                .name("123456785678901")
                .price(100000000.00000001)
                .units("units")
                .imageUrl("test url")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_price_less_than_0() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(-1.0)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is("negative price")));

        assertEquals(0, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_units_empty() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(1.23)
                .units(null)
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        product = Product.builder()
                .name("test")
                .price(1.23)
                .units("")
                .imageUrl("test url")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(0, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_units_length_more_than_50() throws Exception {

        String stringWith50Length = "12345678901234567890123456789012345678901234567890";

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("4567890")
                .price(1.23)
                .units(stringWith50Length)
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        product = Product.builder()
                .name("123456778901")
                .price(1.23)
                .units(stringWith50Length + "1")
                .imageUrl("test url")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_url_empty() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(1.23)
                .units("units")
                .imageUrl("")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        product = Product.builder()
                .name("test")
                .price(1.23)
                .units("units")
                .imageUrl(null)
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        assertEquals(0, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_units_length_more_than_200() throws Exception {

        String stringWith50Length = "12345678901234567890123456789012345678901234567890";

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("4567890")
                .price(1.23)
                .units("units")
                .imageUrl(stringWith50Length + stringWith50Length + stringWith50Length + stringWith50Length)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        product = Product.builder()
                .name("123456778901")
                .price(1.23)
                .units("123456788901")
                .imageUrl(stringWith50Length + stringWith50Length + stringWith50Length + stringWith50Length + "1")
                .build();

        json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
    }

    @Test
    void should_not_add_product_when_name_exist() throws Exception {

        List<ProductEntity> productEntityList = productRepository.findAll();
        assertEquals(0, productEntityList.size());
        Product product = Product.builder()
                .name("test")
                .price(1.23)
                .units("units")
                .imageUrl("test url")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("success")));

        Product secondProduct = Product.builder()
                .name("test")
                .price(2.34)
                .units("units2")
                .imageUrl("test url2")
                .build();

        json = objectMapper.writeValueAsString(secondProduct);

        mockMvc.perform(post("/product")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is("exist name")));


        productEntityList = productRepository.findAll();
        assertEquals(1, productEntityList.size());
        assertEquals(product.getName(), productEntityList.get(0).getName());
        assertEquals(product.getImageUrl(), productEntityList.get(0).getImageUrl());
        assertEquals(product.getUnits(), productEntityList.get(0).getUnits());
        assertEquals(product.getPrice(), productEntityList.get(0).getPrice());
    }

}