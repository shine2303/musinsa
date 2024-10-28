package com.musinsa.backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Report1Response {
    private Long totalAmount;  // 모든 카테고리 최저가의 총합
    private List<Row> row;

    @Getter
    public static class Row {
        private String categoryName;
        private String brandName;
        private Long price;
    }
}
