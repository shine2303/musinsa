package com.musinsa.backend.repository;

import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import com.musinsa.backend.dto.Report3Response;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SearchMapper {
    //간편 네이밍으로 주석 추가

    /**
     * 카테고리별 최저가격 브랜드와 상품 가격 조회
     *
     * @return 카테고리별 최저가 브랜드 및 가격 정보 목록
     */
    List<Report1Response.Row> search1();


    /**
     * 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리별 상품 가격, 총액 조회
     *
     * @return 브랜드별 카테고리 가격 정보 목록
     */
    List<Report2Response.Info> search2();

    /**
     * 특정 카테고리의 존재 여부 확인
     *
     * @param categoryName 카테고리명
     * @return 카테고리 존재 여부
     */
    boolean isExistCategory(String categoryName);

    /**
     * 특정 카테고리에서 최저, 최고 가격 브랜드와 상품 가격 조회
     *
     * @param categoryName 카테고리명
     * @return 카테고리별 최저/최고가 정보
     */
    List<Report3Response.Info> search3(String categoryName);
}
