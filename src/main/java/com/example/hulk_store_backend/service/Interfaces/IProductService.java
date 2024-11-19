package com.example.hulk_store_backend.service.Interfaces;

import com.example.hulk_store_backend.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    Page<ProductDTO> findAllPage(Pageable pageable);
    ProductDTO findById(Long id);
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(Long id, ProductDTO productDTO);
    String destroy(Long id);
}
