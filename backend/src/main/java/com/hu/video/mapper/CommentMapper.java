package com.hu.video.mapper;

import com.hu.video.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentMapper {
    List<Comment> getCommentsByVideoId(Long videoId);
    int addComment(Comment comment);
}
