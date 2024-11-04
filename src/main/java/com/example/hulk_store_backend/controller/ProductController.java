package com.example.hulk_store_backend.controller;

import com.example.hulk_store_backend.dto.ProductDTO;
import com.example.hulk_store_backend.response.ApiResponse;
import com.example.hulk_store_backend.service.Interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> findAll(){
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> findById(@PathVariable Long id){
        return new ResponseEntity<>(ApiResponse.success(this.productService.findById(id)), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(this.productService.save(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(this.productService.update(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.productService.destroy(id), HttpStatus.OK);
    }
}
