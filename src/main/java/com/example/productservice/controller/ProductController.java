package com.example.productservice.controller;

import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    ProductController(ProductService productService){
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        System.out.println("this is hitted");
        Product product =  productService.getProductById(id);
        return new ResponseEntity<>(product , HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public List<Product> getAllProducts(){
        System.out.println("this is for getALlProducts");
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product createProduct(@RequestBody  Product product){
        return new Product();
    }


    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id , @RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id , @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        return;
    }
}
