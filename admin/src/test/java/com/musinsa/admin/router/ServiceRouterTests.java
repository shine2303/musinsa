package com.musinsa.admin.router;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.dto.CategoryDto;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.dto.ProductDto;
import com.musinsa.admin.service.BrandService;
import com.musinsa.admin.service.CategoryService;
import com.musinsa.admin.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceRouterTests {

    @Mock
    private BrandService brandService;
    @Mock
    private ProductService productService;
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ServiceRouter serviceRouter;

    private ManageRequest brandRequest;
    private ManageRequest productRequest;
    private BrandDto brandDto;
    private ProductDto productDto;
    private CategoryEntity categoryEntity;
    private BrandEntity brandEntity;
    private ProductEntity productEntity;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        brandDto = BrandDto.builder()
                .name("Nike")
                .build();

        categoryEntity = CategoryEntity.builder()
                .name("상의")
                .build();

        brandEntity = BrandEntity.builder()
                .name("Nike")
                .build();

        CategoryDto categoryDto = CategoryDto.builder()
                .name("상의")
                .build();

        productDto = ProductDto.builder()
                .id(1L)
                .name("맨투맨")
                .price(50000)
                .category(categoryDto)
                .brand(brandDto)
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
    @DisplayName("브랜드 추가 성공 테스트")
    void execute_InsertBrand_Success() {
        // given
        brandRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.BRAND)
                .action(ManageRequest.Action.INSERT)
                .data(objectMapper.valueToTree(brandDto))
                .build();

        given(brandService.existsByName(anyString())).willReturn(false);
        given(brandService.insertBrand(any(BrandDto.class))).willReturn(brandDto);

        // when
        Object result = serviceRouter.execute(brandRequest);

        // then
        assertThat(result).isInstanceOf(BrandDto.class);
        assertThat(((BrandDto) result).getName()).isEqualTo(brandDto.getName());
        verify(brandService).existsByName(brandDto.getName());
        verify(brandService).insertBrand(any(BrandDto.class));
    }

    @Test
    @DisplayName("상품 추가 성공 테스트")
    void execute_InsertProduct_Success() {
        // given
        productRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.PRODUCT)
                .action(ManageRequest.Action.INSERT)
                .data(objectMapper.valueToTree(productDto))
                .build();

        given(categoryService.findByName(anyString())).willReturn(Optional.of(categoryEntity));
        given(brandService.findByName(anyString())).willReturn(Optional.of(brandEntity));
        given(productService.existsByNameAndPriceAndCategoryAndBrand(any(), any(), any()))
                .willReturn(false);
        given(productService.saveProduct(any(), any(), any())).willReturn(productDto);

        // when
        Object result = serviceRouter.execute(productRequest);

        // then
        assertThat(result).isInstanceOf(ProductDto.class);
        ProductDto resultDto = (ProductDto) result;
        assertThat(resultDto.getName()).isEqualTo(productDto.getName());
        assertThat(resultDto.getPrice()).isEqualTo(productDto.getPrice());
        verify(productService).saveProduct(any(), any(), any());
    }

    @Test
    @DisplayName("상품 수정 성공 테스트")
    void execute_UpdateProduct_Success() {
        // given
        productRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.PRODUCT)
                .action(ManageRequest.Action.UPDATE)
                .data(objectMapper.valueToTree(productDto))
                .build();

        given(productService.findById(anyLong())).willReturn(Optional.of(productEntity));
        given(categoryService.findByName(anyString())).willReturn(Optional.of(categoryEntity));
        given(brandService.findByName(anyString())).willReturn(Optional.of(brandEntity));
        given(productService.saveProduct(any(), any(), any())).willReturn(productDto);

        // when
        Object result = serviceRouter.execute(productRequest);

        // then
        assertThat(result).isInstanceOf(ProductDto.class);
        ProductDto resultDto = (ProductDto) result;
        assertThat(resultDto.getName()).isEqualTo(productDto.getName());
        verify(productService).saveProduct(any(), any(), any());
    }

    @Test
    @DisplayName("상품 삭제 성공 테스트")
    void execute_DeleteProduct_Success() {
        // given
        productRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.PRODUCT)
                .action(ManageRequest.Action.DELETE)
                .data(objectMapper.valueToTree(productDto))
                .build();

        given(productService.findById(anyLong())).willReturn(Optional.of(productEntity));

        // when
        Object result = serviceRouter.execute(productRequest);

        // then
        assertThat(result).isInstanceOf(ProductDto.class);
        verify(productService).deleteProduct(productDto.getId());
    }

    @Test
    @DisplayName("상품 파라미터 유효성 검사 실패 테스트")
    void validateProductParams_Invalid() {
        // given
        ProductDto invalidProductDto = ProductDto.builder()
                .name("")
                .price(-1000)
                .build();

        productRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.PRODUCT)
                .action(ManageRequest.Action.INSERT)
                .data(objectMapper.valueToTree(invalidProductDto))
                .build();

        // when & then
        assertThatThrownBy(() -> serviceRouter.execute(productRequest))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.PRODUCT_NAME_INVALID);
    }

    @Test
    @DisplayName("존재하지 않는 카테고리로 상품 추가 실패 테스트")
    void execute_InsertProduct_CategoryNotFound() {
        // given
        productRequest = ManageRequest.builder()
                .entityType(ManageRequest.EntityType.PRODUCT)
                .action(ManageRequest.Action.INSERT)
                .data(objectMapper.valueToTree(productDto))
                .build();

        given(categoryService.findByName(anyString())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> serviceRouter.execute(productRequest))
                .isInstanceOf(BusinessException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.CATEGORY_NOT_FOUND);
    }

}
