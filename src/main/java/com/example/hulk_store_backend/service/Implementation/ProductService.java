package com.example.hulk_store_backend.service.Implementation;

import com.example.hulk_store_backend.dto.ProductDTO;
import com.example.hulk_store_backend.model.Category;
import com.example.hulk_store_backend.model.Product;
import com.example.hulk_store_backend.repository.ProductRepository;
import com.example.hulk_store_backend.service.Interfaces.IProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService implements IProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> findAll() {
        return this.productRepository.findAll().stream().map(entity -> this.modelMapper.map(entity, ProductDTO.class)).toList();
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id).orElseGet(() -> null);
        if (product == null) return null;
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        return this.modelMapper.map(this.productRepository.save(this.modelMapper.map(productDTO, Product.class)), ProductDTO.class);
    }

    @Override
    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product product = this.productRepository.findById(id).orElseGet(() -> null);
        if (product == null) return null;
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCategoryId(productDTO.getCategoryId());
        return this.modelMapper.map(this.productRepository.save(product), ProductDTO.class);
    }

    @Override
    public String destroy(Long id) {
        Product product = this.productRepository.findById(id).orElseGet(() -> null);
        if (product == null) return "Product not found";
        this.productRepository.delete(product);
        return "Product deleted successfully";
    }
}
