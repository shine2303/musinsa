package com.musinsa.admin.service;


import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import com.musinsa.admin.dto.ProductDto;
import com.musinsa.admin.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    private ProductDto productDto;
    private ProductEntity productEntity;
    private CategoryEntity categoryEntity;
    private BrandEntity brandEntity;

    @BeforeEach
    void setUp() {
        categoryEntity = CategoryEntity.builder()
                .name("상의")
                .build();

        brandEntity = BrandEntity.builder()
                .name("Nike")
                .build();

        productDto = ProductDto.builder()
                .name("맨투맨")
                .price(50000)
                .build();

        productEntity = ProductEntity.builder()
                .id(1L)
                .name("맨투맨")
                .price(50000L)
                .category(categoryEntity)
                .brand(brandEntity)
                .build();
    }

    @Test
    @DisplayName("상품 저장 성공 테스트")
    void saveProduct_Success() {
        // given
        given(productRepository.save(any(ProductEntity.class))).willReturn(productEntity);

        // when
        ProductDto savedProduct = productService.saveProduct(productDto, categoryEntity, brandEntity);

        // then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getName()).isEqualTo(productDto.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(productDto.getPrice());
        verify(productRepository).save(any(ProductEntity.class));
    }

    @Test
    @DisplayName("상품 중복 체크 테스트 - 중복 존재")
    void existsByNameAndPriceAndCategoryAndBrand_ExistsTrue() {
        // given
        given(productRepository.existsByNameAndPriceAndCategoryAndBrand(
                productDto.getName(),
                productDto.getPrice(),
                categoryEntity,
                brandEntity
        )).willReturn(true);

        // when
        boolean exists = productService.existsByNameAndPriceAndCategoryAndBrand(
                productDto,
                categoryEntity,
                brandEntity
        );

        // then
        assertThat(exists).isTrue();
        verify(productRepository).existsByNameAndPriceAndCategoryAndBrand(
                productDto.getName(),
                productDto.getPrice(),
                categoryEntity,
                brandEntity
        );
    }

    @Test
    @DisplayName("ID로 상품 조회 성공 테스트")
    void findById_Success() {
        // given
        long productId = 1L;
        given(productRepository.findById(productId)).willReturn(Optional.of(productEntity));

        // when
        Optional<ProductEntity> foundProduct = productService.findById(productId);

        // then
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getId()).isEqualTo(productId);
        assertThat(foundProduct.get().getName()).isEqualTo(productDto.getName());
        verify(productRepository).findById(productId);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 상품 조회 테스트")
    void findById_NotFound() {
        // given
        long productId = 999L;
        given(productRepository.findById(productId)).willReturn(Optional.empty());

        // when
        Optional<ProductEntity> foundProduct = productService.findById(productId);

        // then
        assertThat(foundProduct).isEmpty();
        verify(productRepository).findById(productId);
    }

}
