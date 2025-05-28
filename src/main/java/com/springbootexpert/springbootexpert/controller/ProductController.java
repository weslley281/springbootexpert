package com.springbootexpert.springbootexpert.controller;

import com.springbootexpert.springbootexpert.model.Product;
import com.springbootexpert.springbootexpert.model.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product){
        System.out.println("Produto recebido: " + product);
        productRepository.save(product);
        return product;
    }

    @GetMapping({"", "/"})
    public String ok() {
        return "<h1>Deu certo cara</h1>";
    }

    @GetMapping("/all")
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/id/{product_id}")
    public Product getById(@PathVariable String product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        return product.orElse(null);
    }
}
