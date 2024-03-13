package com.hu.video.mapper;

import com.hu.video.entity.vo.CommentedStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentedStarMapper {

    CommentedStar getCommentedStar(@Param("userId") Long userId, @Param("videoId") Long videoId);

    int insertOne(@Param("userId") Long userId, @Param("videoId") Long videoId, @Param("starNum") int starNum);

    List<CommentedStar> getCommentedStarByVideoId(@Param("videoId") Long videoId);

    int count(Long videoId);
}
