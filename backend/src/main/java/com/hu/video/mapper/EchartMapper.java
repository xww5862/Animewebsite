package com.hu.video.mapper;

import com.hu.video.entity.TVideotype;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EchartMapper {
   List<TVideotype> selectAllVideoType();
}
