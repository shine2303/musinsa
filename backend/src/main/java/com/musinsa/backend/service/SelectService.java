package com.musinsa.backend.service;

import com.musinsa.backend.dto.Search1Response;
import com.musinsa.backend.repository.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SelectService {

    private final SearchMapper searchMapper;


    public Search1Response search1() {

        List<Search1Response.Row> res = searchMapper.searchFun1();

        long totalAmount = res.stream()
                .mapToLong(Search1Response.Row::getPrice)
                .sum();

        return Search1Response.builder()
                .totalAmount(totalAmount)
                .row(res)
                .build();
    }
}
