package com.twuc.shopping.controller;

import com.twuc.shopping.dto.Product;
import com.twuc.shopping.dto.StoreSystemMessageResponse;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product) {
        if (productService.addProduct(product).equals(StoreSystemMessageResponse.SUCCESS)) {
            return ResponseEntity.ok().body(StoreSystemMessageResponse.SUCCESS);
        }
        return ResponseEntity.badRequest().body(StoreSystemMessageResponse.ERROR);
    }
}
