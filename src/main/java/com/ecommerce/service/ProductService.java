package com.ecommerce.service;

import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Product createProduct(Product product, Long userId){
        Optional<User> byId = userRepository.findById(userId);
    if(byId.isPresent()){
        User user=byId.get();
        product.setUser(user);
        return productRepository.save(product);
    }else {
        throw new RuntimeException("UserId not Found"+userId);
    }
    }


    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
}
