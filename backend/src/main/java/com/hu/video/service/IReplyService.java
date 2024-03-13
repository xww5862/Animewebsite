package com.hu.video.service;

import com.hu.video.entity.Reply;

import java.util.List;

public interface IReplyService {

    List<Reply> getReplyCommentsByCommentId(Long commentId);

    int addReplyComment(Reply reply);
}
