package com.hu.video.service.impl;

import com.hu.video.entity.Reply;
import com.hu.video.mapper.TReplyMapper;
import com.hu.video.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReplyServiceImpl implements IReplyService {
    @Autowired
    private TReplyMapper replyMapper;

    @Override
    public List<Reply> getReplyCommentsByCommentId(Long commentId) {
        return replyMapper.getReplyCommentsByCommentId(commentId);
    }

    @Override
    public int addReplyComment(Reply reply) {
        return replyMapper.addReplyComment(reply);
    }
}
