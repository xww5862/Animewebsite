package com.hu.video.controller;


import com.hu.video.entity.Comment;
import com.hu.video.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("addComment")
    public int addComment(Comment comment){
        Comment comment1 = new Comment();
        comment1.setCommentUserId(comment.getCommentUserId());
        comment1.setContent(comment.getContent());
        comment1.setVideoId(comment.getVideoId());

        long s=new Date().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(s);
        comment1.setCreateTime(date);

        return commentService.addComment(comment1);
    }

    @RequestMapping("getComments")
    @ResponseBody
    public List<Comment> getComments(Long videoId){
        List<Comment> CommentList = commentService.getCommentsByVideoId(videoId);
        return CommentList;
    }
}
