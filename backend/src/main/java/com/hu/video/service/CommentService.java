package com.hu.video.service;

import com.hu.video.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByVideoId(Long videoId);
    int addComment(Comment comment);
}
