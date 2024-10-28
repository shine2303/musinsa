package com.musinsa.admin.service;


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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTests {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    private BrandDto brandDto;
    private BrandEntity brandEntity;

    @BeforeEach
    void setUp() {
        brandDto = BrandDto.builder()
                .name("Nike")
                .build();

        brandEntity = BrandEntity.builder()
                .name("Nike")
                .build();
    }

    @Test
    @DisplayName("브랜드 저장 성공 테스트")
    void insertBrand_Success() {
        // given
        given(brandRepository.save(any(BrandEntity.class))).willReturn(brandEntity);

        // when
        BrandDto result = brandService.insertBrand(brandDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo(brandDto.getName());
        verify(brandRepository).save(any(BrandEntity.class));
    }

    @Test
    @DisplayName("브랜드명으로 존재여부 확인 테스트")
    void existsByName_Success() {
        // given
        String brandName = "Nike";
        given(brandRepository.existsByName(brandName)).willReturn(true);

        // when
        boolean exists = brandService.existsByName(brandName);

        // then
        assertThat(exists).isTrue();
        verify(brandRepository).existsByName(brandName);
    }

    @Test
    @DisplayName("브랜드명으로 조회 성공 테스트")
    void findByName_Success() {
        // given
        String brandName = "Nike";
        given(brandRepository.findByName(brandName)).willReturn(Optional.of(brandEntity));

        // when
        Optional<BrandEntity> result = brandService.findByName(brandName);

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo(brandName);
        verify(brandRepository).findByName(brandName);
    }

    @Test
    @DisplayName("존재하지 않는 브랜드명으로 조회 테스트")
    void findByName_NotFound() {
        // given
        String brandName = "Non-existing";
        given(brandRepository.findByName(brandName)).willReturn(Optional.empty());

        // when
        Optional<BrandEntity> result = brandService.findByName(brandName);

        // then
        assertThat(result).isEmpty();
        verify(brandRepository).findByName(brandName);
    }
}
