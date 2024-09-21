package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.Category;
import com.codegym.jwt_spring_boot.model.Product;
import com.codegym.jwt_spring_boot.model.VariantProduct;
import com.codegym.jwt_spring_boot.repository.IProductRepo;
import com.codegym.jwt_spring_boot.repository.IVariantProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductRepo iProductRepo;

    @Autowired
    IVariantProductRepo iVariantProductRepo;

    @GetMapping("/{id}")
    public ResponseEntity<?> products(@PathVariable int id){
        Product product = iProductRepo.findById(id).get();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/variant/{idP}")
    public ResponseEntity<?> variants(@PathVariable int idP){
        List<VariantProduct> variantProducts = iVariantProductRepo.findAllByProductId(idP);
        return new ResponseEntity<>(variantProducts, HttpStatus.OK);
    }

}
