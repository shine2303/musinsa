package com.musinsa.admin.dto;

import com.musinsa.admin.entity.BrandEntity;
import com.musinsa.admin.entity.CategoryEntity;
import com.musinsa.admin.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private long price;
    private CategoryDto category;
    private BrandDto brand;
    private boolean isActive = true;

    @Builder
    private ProductDto(Long id, String name, long price, CategoryDto category, BrandDto brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }

    public ProductEntity toEntity(CategoryEntity categoryEntity, BrandEntity brandEntity) {
        ProductEntity.ProductEntityBuilder builder = ProductEntity.builder()
                .name(this.name)
                .price(this.price)
                .category(categoryEntity)
                .brand(brandEntity)
                .isActive(this.isActive);

        // id가 있는 경우에만 설정 (수정 시)
        if (this.id != null) {
            builder.id(this.id);
        }

        return builder.build();
    }

    public static ProductDto from(ProductEntity entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .category(CategoryDto.from(entity.getCategory()))
                .brand(BrandDto.from(entity.getBrand()))
                .build();
    }

//    public CategoryEntity getCategoryEntity() {
//        return CategoryEntity.builder()
//                .name(this.category.getName())
//                .build();
//    }
//
//    public BrandEntity getBrandEntity() {
//        return BrandEntity.builder()
//                .name(this.brand.getName())
//                .build();
//    }
}