package com.musinsa.admin.service;

import com.musinsa.admin.domain.BrandEntity;
import com.musinsa.admin.domain.CategoryEntity;
import com.musinsa.admin.domain.ProductEntity;
import com.musinsa.admin.dto.ProductDto;
import com.musinsa.admin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDto saveProduct(ProductDto productDto, CategoryEntity categoryEntity, BrandEntity brandEntity) {

        ProductEntity savedProduct = productRepository.save(productDto.toEntity(categoryEntity, brandEntity));
        return ProductDto.from(savedProduct);
    }

    public boolean existsByNameAndPriceAndCategoryAndBrand(ProductDto productDto,CategoryEntity categoryEntity, BrandEntity brandEntity) {
        return productRepository.existsByNameAndPriceAndCategoryAndBrand(
                productDto.getName(),
                productDto.getPrice(),
                categoryEntity,
                brandEntity);
    }

    public Optional<ProductEntity> findById(long id) {
        return productRepository.findById(id);
    }

    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }
}
