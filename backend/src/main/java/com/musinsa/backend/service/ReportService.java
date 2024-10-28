package com.musinsa.backend.service;

import com.musinsa.backend.common.ErrorCode;
import com.musinsa.backend.common.exception.BusinessException;
import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import com.musinsa.backend.repository.SearchMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReportService {

    private final SearchMapper searchMapper;


    public Report1Response report1() {
        //예외 설명 : exception handler에서 예외를 잡아줌. ex. sqlTimeout, sql 문법 등

        //1. 조회
        List<Report1Response.Row> res = search1();

        //2. 결과 생성
        return makeResult1(res);
    }

    private List<Report1Response.Row> search1(){
        return searchMapper.search1();
    }

    private Report1Response makeResult1(List<Report1Response.Row> res){

        //1개의 상품은 존재해야함(과제 조건) + 조건이 없었다면 0건은 200처리하여 정상 응답처리했을 것
        if(CollectionUtils.isEmpty(res)){
            throw new BusinessException(ErrorCode.NON_RESULT_ERROR);
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
        List<Report2Response.Info> res = search2();

        //2. 결과 생성
        return makeResult2(res);
    }

    private List<Report2Response.Info> search2(){
        return searchMapper.search2();
    }

    private Report2Response makeResult2(List<Report2Response.Info> res){
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

}
