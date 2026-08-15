package com.ms.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.product_service.entitty.Product;
import com.ms.product_service.repository.ProductRepository;

@RestController
@RequestMapping("/{product}")
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@GetMapping
	public List<Product> getAllProduct(){
		return productRepository.findAll();		
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Product> getById(@PathVariable Long productId){
		Product product =  productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("product id not found " +productId));
		return ResponseEntity.ok(product);
	}
}
