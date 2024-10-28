package com.musinsa.admin.dto;

import com.musinsa.admin.entity.CategoryEntity;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private String name;

    @Builder
    private CategoryDto(String name) {
        this.name = name;
    }

    public static CategoryDto from(CategoryEntity entity) {
        return CategoryDto.builder()
                .name(entity.getName())
                .build();
    }
}