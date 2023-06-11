package com.springmvc.demo.controllers;

import com.springmvc.demo.models.Category;
import com.springmvc.demo.models.Product;
import com.springmvc.demo.repositories.CategoryRepository;
import com.springmvc.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@Controller
@RequestMapping(path = "products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired // Inject "categoryRepository" - Dependency Injection
    private CategoryRepository categoryRepository;

    // http:localhost:8083/products/getProductsByCategoryID/C0103
    @GetMapping(value = "/getProductsByCategoryID/{categoryID}", produces = "application/json")
    @ResponseBody
    public Iterable<Product> getProductsByCategoryID(ModelMap modelMap, @PathVariable String categoryID) {
        return productRepository.findByCategoryID(categoryID);

    }

    @GetMapping(value = "/changeCategory/{productID}", produces = "application/json")
    @ResponseBody
    public Product changeCategory(ModelMap modelMap, @PathVariable String productID) {
        categoryRepository.findAll();
        return productRepository.findById(productID).get();

    }

    @GetMapping(value = "/insertProduct", produces = "application/json")
    @ResponseBody
    public Iterable<Category> insertProduct(ModelMap modelMap) {
        modelMap.addAttribute("product", new Product());
        modelMap.addAttribute("categories", categoryRepository.findAll());
        return categoryRepository.findAll();

    }

    @PostMapping(value = "/insertProduct", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> insertProduct(
            @RequestBody Product product) {

        try {
            // random uuid => productID
            // String productID, String categoryID, String productName, int price, String
            // description
            product.setProductID(UUID.randomUUID().toString());
            productRepository.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
    }

    @PostMapping(value = "/deleteProduct/{productID}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(ModelMap modelMap, @PathVariable String productID) {
        try {
            productRepository.deleteById(productID);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create product");
        }
     
    }

}
