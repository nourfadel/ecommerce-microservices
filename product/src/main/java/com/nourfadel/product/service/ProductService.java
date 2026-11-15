package com.nourfadel.product.service;

import com.nourfadel.product.dto.ProductRequest;
import com.nourfadel.product.dto.ProductResponse;
import com.nourfadel.product.entity.Product;
import com.nourfadel.product.exception.ProductNotFoundException;
import com.nourfadel.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // create product
    @Transactional
    public ProductResponse create(ProductRequest request){
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getQuantity());

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getStockQuantity())
                .build();

        return response;
    }

    // find All products
    public List<ProductResponse> findAll(){
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // find product by Id
    public ProductResponse findById(Long productId){
        Product product = getProductOrThrow(productId);
        return mapToResponse(product);
    }

    // update product
    @Transactional
    public ProductResponse update(Long productId, ProductRequest request){
        Product newProduct = getProductOrThrow(productId);
        newProduct.setName(request.getName());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setStockQuantity(request.getQuantity());

        Product updatedProduct = productRepository.save(newProduct);
        return mapToResponse(updatedProduct);
    }

    // delete product
    @Transactional
    public void deleteById(Long productId){
        Product product = getProductOrThrow(productId);
        productRepository.delete(product);
    }

    public Product getProductOrThrow(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));
        return product;
    }

}















