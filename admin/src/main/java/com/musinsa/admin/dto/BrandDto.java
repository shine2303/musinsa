package com.musinsa.admin.dto;

import com.musinsa.admin.domain.BrandEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BrandDto {
    private String name;

    public BrandEntity toEntity() {
        return BrandEntity.builder().name(name).build();
    }

    @Builder
    private BrandDto(String name) {
        this.name = name;
    }
}
