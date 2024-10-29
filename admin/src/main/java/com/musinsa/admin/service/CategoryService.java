package com.musinsa.admin.service;

import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    @Transactional(readOnly = true)
    public Optional<CategoryEntity> findByName(String name){
        return categoryRepository.findByName(name);
    }
}
