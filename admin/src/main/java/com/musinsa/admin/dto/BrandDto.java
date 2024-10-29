package com.musinsa.admin.dto;

import com.musinsa.admin.entity.BrandEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BrandDto {
    private Long id;
    private String name;

    public BrandEntity toEntity() {
        return BrandEntity.builder()
                .name(name)
                .build();
    }

    @Builder
    private BrandDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BrandDto from(BrandEntity entity) {
        return BrandDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}