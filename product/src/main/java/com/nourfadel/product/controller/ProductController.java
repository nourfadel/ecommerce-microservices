package com.nourfadel.product.controller;

import com.nourfadel.product.dto.ProductRequest;
import com.nourfadel.product.dto.ProductResponse;
import com.nourfadel.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(){
        List<ProductResponse> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    // find product by Id
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId){
        ProductResponse response = productService.findById(productId);
        return ResponseEntity.ok(response);
    }

    // create product
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // update product
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long productId,
                                                  @Valid @RequestBody ProductRequest request){
        ProductResponse response = productService.update(productId,request);
        return ResponseEntity.ok(response);
    }

    // delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId){
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

}














