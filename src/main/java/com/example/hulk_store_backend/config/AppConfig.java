package com.example.hulk_store_backend.config;


import com.example.hulk_store_backend.dto.ProductDTO;
import com.example.hulk_store_backend.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.typeMap(Product.class, ProductDTO.class).addMapping(
                Product::getCategoryId, ProductDTO::setCategoryId
        );

        return modelMapper;
    }
}
