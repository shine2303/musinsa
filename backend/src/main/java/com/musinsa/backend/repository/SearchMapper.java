package com.musinsa.backend.repository;

import com.musinsa.backend.dto.Report1Response;
import com.musinsa.backend.dto.Report2Response;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Report1Response.Row> search1();
    List<Report2Response.Info> search2();
}
