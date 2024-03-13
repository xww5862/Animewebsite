package com.hu.video.mapper;

import com.hu.video.entity.Danmu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TDanmuMapper {
    int save(Danmu danmu);
    int count(Long videoId);
    List<Danmu> selectAllByVideoId(Long videoId);
}
