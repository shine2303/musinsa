package com.musinsa.backend.service;

import com.musinsa.backend.common.ErrorCode;
import com.musinsa.backend.common.exception.BusinessException;
import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import com.musinsa.backend.dto.Report3Response;
import com.musinsa.backend.repository.SearchMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReportService {

    private final SearchMapper searchMapper;

    //예외 설명 : exception handler에서 예외를 잡아줌. ex. sqlTimeout, sql 문법 등

    public Report1Response report1() {

        //1. 조회
        List<Report1Response.Row> res = searchMapper.search1();

        //2. 결과 생성
        return makeResult1(res);
    }

    private Report1Response makeResult1(List<Report1Response.Row> res){

        //중요!!! 과제 조건에 모든 브랜드의 모든 카테고리에는 1개는 보장하기에 다음 2,3api에서는 예외처리를 생략
        // 1개의 상품은 존재해야하는 조건이 없었다면 0건은 status code 200처리하여 정상 응답처리했을 것. 즉 0건처리
        if(CollectionUtils.isEmpty(res)){
            throw new BusinessException(ErrorCode.NOT_FOUND_RESULT);
        }

        long totalAmount = res.stream()
            .mapToLong(Report1Response.Row::getPrice)
            .sum();

        return Report1Response.builder()
            .totalAmount(totalAmount)
            .row(res)
            .build();
    }

    public Report2Response report2() {
        //1. 조회
        List<Report2Response.Info> res = searchMapper.search2();

        //2. 결과 생성
        return makeResult2(res);
    }

    private Report2Response makeResult2(List<Report2Response.Info> res){
        //데이터는 무조건 존재한다는 가정 -> makeResult1에서 설명
        String brandName = res.get(0).getBrandName();
        long totalAmount = res.stream()
                .mapToLong(Report2Response.Info::getPrice)
                .sum();

        List<Report2Response.Row> rows = res.stream()
                .map(info -> Report2Response.Row.builder()
                        .categoryName(info.getCategoryName())
                        .price(info.getPrice())
                        .build())
                .toList();

        Report2Response.LowestPriceInfo lowestPriceInfo = Report2Response.LowestPriceInfo.builder()
                .brandName(brandName)
                .totalAmount(totalAmount)
                .categories(rows)
                .build();

        return Report2Response.builder().lowestPrice(lowestPriceInfo).build();
    }

    public Report3Response report3(String categoryName) {

        if(null == categoryName){
            throw new BusinessException(ErrorCode.BAD_REQUEST, "카테고리 파리미터가 잘못되었습니다.");
        }
        String cName = categoryName.toLowerCase();

        //1. 카테고리 조회
        if(!searchMapper.isExistCategory(cName)){
            throw new BusinessException(ErrorCode.NOT_FOUND_CATEGORY);
        }

        //2. 조회
        List<Report3Response.Info> res = searchMapper.search3(cName);

        //3. 결과 생성
        //데이터는 무조건 존재한다는 가정 -> makeResult1에서 설명
        return makeResult3(res.get(0));
    }

    private Report3Response makeResult3(Report3Response.Info info) {
        List<Report3Response.Row> minPriceList = Collections.singletonList(
            Report3Response.Row.builder()
                .brandName(info.getMinBrandName())
                .price(info.getMinPrice())
                .build()
        );

        List<Report3Response.Row> maxPriceList = Collections.singletonList(
            Report3Response.Row.builder()
                .brandName(info.getMaxBrandName())
                .price(info.getMaxPrice())
                .build()
        );
        return Report3Response.builder()
                .categoryName(info.getCategoryName())
                .minPriceInfo(minPriceList)
                .maxPriceInfo(maxPriceList)
                .build();
    }
}
