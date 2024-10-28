package com.musinsa.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Report2Response {

    @JsonProperty("최저가")
    private final LowestPriceInfo lowestPrice;

    @Getter
    @Builder
    public static class LowestPriceInfo {
        @JsonProperty("브랜드")
        private String brandName;

        @JsonProperty("카테고리")
        private List<Row> categories;

        @JsonProperty("총액")
        private long totalAmount;
    }

    @Getter
    @Builder
    public static class Row {
        @JsonProperty("카테고리")
        private String categoryName;

        @JsonProperty("가격")
        private long price;
    }

    @Getter
    @Builder
    public static class Info {
        private String brandName;
        private String categoryName;
        private long price;
    }
}
