package com.musinsa.admin.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private String name;
    private long price;
    private int categoryId;
    private int brandId;
}