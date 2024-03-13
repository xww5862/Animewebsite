package com.hu.video.mapper;


import com.hu.video.entity.Reply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TReplyMapper {
    List<Reply> getReplyCommentsByCommentId(Long commentId);

    int addReplyComment(Reply reply);
}
