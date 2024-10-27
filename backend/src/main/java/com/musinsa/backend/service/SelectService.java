package com.musinsa.backend.service;

import com.musinsa.backend.common.ErrorCode;
import com.musinsa.backend.common.exception.BusinessException;
import com.musinsa.backend.dto.Search1Response;
import com.musinsa.backend.dto.Search2Response;
import com.musinsa.backend.repository.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//결과를 가공하는 레이어
@Service
@RequiredArgsConstructor
public class SelectService {

    private final SearchMapper searchMapper;


    public Search1Response search1() {

        try{
            List<Search1Response.Row> res = searchMapper.searchFun1();

            long totalAmount = res.stream()
                    .mapToLong(Search1Response.Row::getPrice)
                    .sum();

            return Search1Response.builder()
                    .totalAmount(totalAmount)
                    .row(res)
                    .build();
        }catch (DataAccessException e){
            throw new BusinessException(ErrorCode.DB_ACCESS_ERROR);
        }
    }

    public Search2Response search2() {
        List<Search2Response.Info> res = null;

        try{
            res = searchMapper.searchFun2();

        }catch (DataAccessException e){
            throw new BusinessException(ErrorCode.DB_ACCESS_ERROR);
        }

        return createRes2(res);
    }

    private Search2Response createRes2(List<Search2Response.Info> res){
        String brandName = res.get(0).getBrandName();
        long totalAmount = res.stream()
                .mapToLong(Search2Response.Info::getPrice)
                .sum();

        List<Search2Response.Row> rows = res.stream()
                .map(info -> Search2Response.Row.builder()
                        .categoryName(info.getCategoryName())
                        .price(info.getPrice())
                        .build())
                .toList();

        Search2Response.LowestPriceInfo lowestPriceInfo = Search2Response.LowestPriceInfo.builder()
                .brandName(brandName)
                .totalAmount(totalAmount)
                .categories(rows)
                .build();

        return Search2Response.builder().lowestPrice(lowestPriceInfo).build();

    }

}
