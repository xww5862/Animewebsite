package com.hu.video.service.impl;

import com.hu.video.entity.Danmu;
import com.hu.video.mapper.TDanmuMapper;
import com.hu.video.service.IDanmuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IDanmuServiceImpl implements IDanmuService {
    @Resource
    private TDanmuMapper mapper;
    @Override
    public int save(Danmu danmu) {
        return mapper.save(danmu);
    }

    @Override
    public int count(Long videoId) {
        return mapper.count(videoId);
    }

    @Override
    public List<Danmu> selectAllByVideoId(Long videoId) {
        return mapper.selectAllByVideoId(videoId);
    }
}
