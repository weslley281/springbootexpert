package com.springbootexpert.springbootexpert.controller;

import com.springbootexpert.springbootexpert.model.Product;
import com.springbootexpert.springbootexpert.model.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping({"", "/", "/all"})
    public ResponseEntity<?> getAll(){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum produto encontrado.");
        } else {
            return ResponseEntity.ok(products);
        }
    }



    @GetMapping("/id/{product_id}")
    public Product getById(@PathVariable String product_id) {
        Optional<Product> product = productRepository.findById(product_id);
        return product.orElse(null);
    }

    @DeleteMapping("{product_id}")
    public ResponseEntity<String> deleteById(@PathVariable String product_id){
        if (productRepository.existsById(product_id)) {
            productRepository.deleteById(product_id);
            return ResponseEntity.ok("Produto deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody Product product){
        if (product.getProduct_id() == null || !productRepository.existsById(product.getProduct_id())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }
}
