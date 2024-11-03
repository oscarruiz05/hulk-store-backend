package com.example.hulk_store_backend.service.Interfaces;

import com.example.hulk_store_backend.dto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    String destroy(Long id);
}
