package com.codegym.jwt_spring_boot.controller.user;

import com.codegym.jwt_spring_boot.model.Category;
import com.codegym.jwt_spring_boot.model.Product;
import com.codegym.jwt_spring_boot.repository.ICategoryRepo;
import com.codegym.jwt_spring_boot.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    ICategoryRepo iCategoryRepo;

    @Autowired
    IProductRepo iProductRepo;
    @GetMapping
    public ResponseEntity<?> categories(){
        List<Category> categories = iCategoryRepo.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> findProductByCategory(@PathVariable int id, @RequestParam(defaultValue = "0") int page){
        Page<Product> products = iProductRepo.findAll(PageRequest.of(page,5));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



}
