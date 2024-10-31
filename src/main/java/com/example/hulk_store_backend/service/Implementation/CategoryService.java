package com.example.hulk_store_backend.service.Implementation;

import com.example.hulk_store_backend.dto.CategoryDTO;
import com.example.hulk_store_backend.model.Category;
import com.example.hulk_store_backend.repository.CategoryRepository;
import com.example.hulk_store_backend.service.Interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService implements ICategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public List<CategoryDTO> findAll() {
        return this.categoryRepository.findAll().stream().map(entity -> this.modelMapper.map(entity, CategoryDTO.class)).toList();
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = this.categoryRepository.findById(id).orElseGet(() -> null);
        if (category == null) return null;
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return this.modelMapper.map(this.categoryRepository.save(this.modelMapper.map(categoryDTO, Category.class)), CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category category = this.categoryRepository.findById(id).orElseGet(() -> null);
        if (category == null) return null;
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return this.modelMapper.map(this.categoryRepository.save(category), CategoryDTO.class);
    }

    @Override
    public String destroy(Long id) {
        Category category = this.categoryRepository.findById(id).orElseGet(() -> null);
        if (category == null) {
            return "Category not found";
        }
        this.categoryRepository.delete(category);
        return "Category deleted";
    }
}
