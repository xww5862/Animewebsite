package com.hu.video.service.impl;


import com.hu.video.entity.Comment;
import com.hu.video.mapper.CommentMapper;
import com.hu.video.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getCommentsByVideoId(Long videoId) {
        return commentMapper.getCommentsByVideoId(videoId);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

}
