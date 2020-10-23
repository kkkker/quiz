package com.twuc.shopping.service;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.dto.StoreSystemMessageResponse;
import com.twuc.shopping.entity.ProductEntity;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProductServiceTest {

    @Autowired
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    @Autowired
    ProductService productService;

    @Test
    void get_all_products() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productEntityList);
        productService.getAllProducts();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void add_product_when_price_less_than_0() {
        Product product = Product.builder()
                .price(-1.0)
                .imageUrl("url")
                .units("units")
                .name("name")
                .build();
        String result = productService.addProduct(product);

        assertEquals(StoreSystemMessageResponse.NEGATIVE_PRICE, result);
    }

}