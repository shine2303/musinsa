package com.musinsa.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "manage api 응답 포맷")
public class ManageResponse {

    @Schema(description = "성공여부", example = "success")
    private String success;
    @Schema(description = "응답코드", example = "200")
    private int code;
    @Schema(description = "메세지", example = "성공/실패 메세지")
    private String message;
    @Schema(description = "데이터", example = "요청 데이터")
    private Object data;
    @Schema(description = "요청시간", example = "")
    private Timestamp timestamp;
}
