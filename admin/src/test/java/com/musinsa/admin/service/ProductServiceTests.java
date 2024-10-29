package com.musinsa.admin.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.dto.CategoryDto;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import com.musinsa.admin.dto.ProductDto;
import com.musinsa.admin.repository.BrandRepository;
import com.musinsa.admin.repository.CategoryRepository;
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

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BrandRepository brandRepository;

    private ManageRequest request;
    private ProductDto productDto = new ProductDto();
    private CategoryEntity categoryEntity;
    private BrandEntity brandEntity;
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 셋업
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("상의");

        BrandDto brandDto = new BrandDto();
        brandDto.setName("Nike");

        productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("반팔티셔츠");
        productDto.setPrice(10000);
        productDto.setCategory(categoryDto);
        productDto.setBrand(brandDto);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.valueToTree(productDto);

        request = ManageRequest.builder()
                .action(ManageRequest.Action.INSERT)
                .entityType(ManageRequest.EntityType.PRODUCT)
                .data(jsonNode)
                .build();

        categoryEntity = CategoryEntity.builder()
                .name("상의")
                .build();

        brandEntity = BrandEntity.builder()
                .name("Nike")
                .build();

        productEntity = ProductEntity.builder()
                .id(1L)
                .name("반팔티셔츠")
                .price(10000L)
                .category(categoryEntity)
                .brand(brandEntity)
                .build();
    }

    @Test
    @DisplayName("상품 수정 성공 테스트")
    void updateProduct_Success() {
        // given
        productDto.setName("수정된 반팔티셔츠");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode updateJsonNode = mapper.valueToTree(productDto);

        request = ManageRequest.builder()
                .action(ManageRequest.Action.UPDATE)
                .entityType(ManageRequest.EntityType.PRODUCT)
                .data(updateJsonNode)
                .build();

        given(productRepository.findById(1L)).willReturn(Optional.of(productEntity));
        given(categoryRepository.findByName("상의")).willReturn(Optional.of(categoryEntity));
        given(brandRepository.findByName("Nike")).willReturn(Optional.of(brandEntity));
        given(productRepository.save(any())).willReturn(productEntity);

        // when
        ProductDto result = productService.updateProduct(request);

        // then
        assertThat(result.getName()).isEqualTo("반팔티셔츠");
        verify(productRepository).save(any());
    }

    @Test
    @DisplayName("상품 삭제 성공 테스트")
    void deleteProduct_Success() {
        // given
        ObjectMapper mapper = new ObjectMapper();
        JsonNode deleteJsonNode = mapper.valueToTree(productDto);

        request = ManageRequest.builder()
                .action(ManageRequest.Action.DELETE)
                .entityType(ManageRequest.EntityType.PRODUCT)
                .data(deleteJsonNode)
                .build();

        given(productRepository.findById(1L)).willReturn(Optional.of(productEntity));

        // when
        ProductDto result = productService.deleteProduct(request);

        // then
        assertThat(result.getId()).isEqualTo(1L);
        verify(productRepository).deleteById(1L);
    }

}
