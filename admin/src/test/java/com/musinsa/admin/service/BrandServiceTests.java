package com.musinsa.admin.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.admin.common.ErrorCode;
import com.musinsa.admin.common.exception.BusinessException;
import com.musinsa.admin.dto.ManageRequest;
import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.dto.BrandDto;
import com.musinsa.admin.repository.BrandRepository;
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
public class BrandServiceTests {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    private ManageRequest request;
    private BrandEntity brandEntity;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        BrandDto brandDto = BrandDto.builder()
                .name("NIKE")
                .build();
        JsonNode jsonNode = objectMapper.valueToTree(brandDto);

        request = ManageRequest.builder()
                .action(ManageRequest.Action.INSERT)
                .entityType(ManageRequest.EntityType.BRAND)
                .data(jsonNode)
                .build();

        brandEntity = BrandEntity.builder()
                .name("NIKE")
                .build();
    }

    @Test
    @DisplayName("새로운 브랜드 등록 성공 테스트")
    void insertBrand_Success() {
        // given
        given(brandRepository.existsByName("NIKE")).willReturn(false);
        given(brandRepository.save(any(BrandEntity.class))).willReturn(brandEntity);

        // when
        BrandDto result = brandService.insertBrand(request);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("NIKE");
        verify(brandRepository).save(any(BrandEntity.class));
    }

    @Test
    @DisplayName("중복된 브랜드명으로 등록 시도 시 예외 발생 테스트")
    void insertBrand_DuplicateName_ThrowsException() {
        // given
        given(brandRepository.existsByName("NIKE")).willReturn(true);

        // when & then
        BusinessException exception = assertThrows(BusinessException.class,
                () -> brandService.insertBrand(request));

        assertThat(exception.getErrorCode()).isEqualTo(ErrorCode.BRAND_ALREADY_EXISTS);
    }
}
