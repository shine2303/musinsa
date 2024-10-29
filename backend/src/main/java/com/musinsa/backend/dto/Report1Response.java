package com.musinsa.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Report1Response {
    @JsonProperty("총액")
    private Long totalAmount;  // 모든 카테고리 최저가의 총합
    private List<Row> row;

    @Getter
    public static class Row {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("가격")
        private Long price;
    }
}
