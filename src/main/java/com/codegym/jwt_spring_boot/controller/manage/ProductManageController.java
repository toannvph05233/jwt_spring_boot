package com.codegym.jwt_spring_boot.controller.manage;

import com.codegym.jwt_spring_boot.model.Product;
import com.codegym.jwt_spring_boot.model.VariantProduct;
import com.codegym.jwt_spring_boot.repository.IProductRepo;
import com.codegym.jwt_spring_boot.repository.IVariantProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/manages")
public class ProductManageController {

        @Autowired
        private IProductRepo iProductRepo;

        @Autowired
        private IVariantProductRepo iVariantProductRepo;

        // 1. Add a new Product
        @PostMapping("/products")
        public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
            Product savedProduct = iProductRepo.save(newProduct);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
        }

        // 2. Update an existing Product
        @PutMapping("/products/{productId}")
        public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product updatedProduct) {
            Optional<Product> existingProduct = iProductRepo.findById(productId);
            if (existingProduct.isPresent()) {
                Product product = existingProduct.get();
                product.setName(updatedProduct.getName());
                product.setCategory(updatedProduct.getCategory());
                // Add other fields if necessary
                Product savedProduct = iProductRepo.save(product);
                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        // 3. Add a new Variant Product to a Product
        @PostMapping("/products/{productId}/variants/add")
        public ResponseEntity<VariantProduct> addVariantProduct(@PathVariable Integer productId, @RequestBody VariantProduct newVariantProduct) {
            Optional<Product> product = iProductRepo.findById(productId);
            if (product.isPresent()) {
                newVariantProduct.setProduct(product.get());
                VariantProduct savedVariant = iVariantProductRepo.save(newVariantProduct);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedVariant);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }

        // 4. Update an existing Variant Product
        @PutMapping("/products/variants/update/{variantId}")
        public ResponseEntity<VariantProduct> updateVariantProduct(@PathVariable Integer variantId, @RequestBody VariantProduct updatedVariantProduct) {
            Optional<VariantProduct> existingVariant = iVariantProductRepo.findById(variantId);
            if (existingVariant.isPresent()) {
                VariantProduct variantProduct = existingVariant.get();
                variantProduct.setColor(updatedVariantProduct.getColor());
                variantProduct.setSize(updatedVariantProduct.getSize());
                variantProduct.setModel(updatedVariantProduct.getModel());
                variantProduct.setPrice(updatedVariantProduct.getPrice());
                // Add other fields if necessary
                VariantProduct savedVariant = iVariantProductRepo.save(variantProduct);
                return ResponseEntity.ok(savedVariant);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }

