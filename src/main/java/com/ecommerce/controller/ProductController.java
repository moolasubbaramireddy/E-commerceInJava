package com.ecommerce.controller;

import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product, @PathVariable("userId") long userId){
        return productService.createProduct(product,userId);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("id") long id){

        return productService.getProductById(id).get();

    }
}
