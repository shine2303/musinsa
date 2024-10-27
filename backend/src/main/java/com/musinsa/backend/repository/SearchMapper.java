package com.musinsa.backend.repository;

import com.musinsa.backend.dto.Search1Response;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Search1Response.Row> searchFun1();
}
