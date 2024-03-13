package com.hu.video.service.impl;

import com.hu.video.mapper.CommentMapper;
import com.hu.video.mapper.CommentedStarMapper;
import com.hu.video.service.CommentedStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentedStarServiceImpl implements CommentedStarService {

    @Autowired
    private CommentedStarMapper commentedStarMapper;
    @Override
    public int count(Long videoId) {
        return commentedStarMapper.count(videoId);
    }
}
