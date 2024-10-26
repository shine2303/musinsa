package com.musinsa.admin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManageRequest {
    @NotNull(message = "action은 필수입니다")
    private Action action;
    @NotNull(message = "entityType은 필수입니다")
    private EntityType entityType;
    @NotNull(message = "data는 필수입니다")
    private JsonNode data;  //동적 데이터를 위해

    @JsonIgnore
    public <T> T convertData(Class<T> type) {
        return new ObjectMapper().convertValue(data, type);
    }

    public enum Action {
        INSERT, UPDATE, DELETE;

        @JsonCreator
        public static Action fromString(String value) {
            return Action.valueOf(value.toUpperCase());
        }
    }

    public enum EntityType {
        PRODUCT, BRAND;

        @JsonCreator
        public static EntityType fromString(String value) {
            return EntityType.valueOf(value.toUpperCase());
        }
    }
}
