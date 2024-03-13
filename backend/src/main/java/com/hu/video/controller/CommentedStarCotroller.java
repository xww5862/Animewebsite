package com.hu.video.controller;


import com.hu.video.entity.Comment;
import com.hu.video.service.CommentedStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("commentstar")
public class CommentedStarCotroller {

    @Autowired
    private CommentedStarService commentedStarService;

    @RequestMapping("getcommentstar_count")
    @ResponseBody
    public Integer getCommentStarCount(Long videoId){
       int count = commentedStarService.count(videoId);
       return count;
    }
}
