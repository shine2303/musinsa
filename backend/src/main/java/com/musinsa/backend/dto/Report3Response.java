package com.musinsa.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class Report3Response {

    @JsonProperty("카테고리")
    private String categoryName;

    @JsonProperty("최저가")
    private final List<Row> minPriceInfo;

    @JsonProperty("최고가")
    private final List<Row> maxPriceInfo;

    @Getter
    @Builder
    public static class Row {
        @JsonProperty("브랜드")
        private String brandName;

        @JsonProperty("가격")
        private long price;
    }

    @Getter
    @Setter
    public static class Info {
        private String categoryName;
        private String minBrandName;
        private long minPrice;
        private String maxBrandName;
        private long maxPrice;
    }
}
