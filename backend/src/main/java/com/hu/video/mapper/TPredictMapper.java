package com.hu.video.mapper;

import com.hu.video.entity.TPredict;
import com.hu.video.entity.TVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TPredictMapper {
    int deleteByPrimaryKey(Long preId);

    int insert(TPredict record);

    int insertSelective(TPredict record);

    TPredict selectByPrimaryKey(Long preId);

    int updateByPrimaryKeySelective(TPredict record);

    int updateByPrimaryKey(TPredict record);

    TPredict selectByVideoId(Long videoId);

    int updateByViodeoId(@Param("preStar") Float preStar, @Param("videoId")Long videoId);
}
