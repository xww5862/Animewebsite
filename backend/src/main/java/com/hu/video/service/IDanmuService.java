package com.hu.video.service;

import com.hu.video.entity.Danmu;

import java.util.List;

public interface IDanmuService {
    int save(Danmu danmu);
    int count(Long videoId);
    List<Danmu> selectAllByVideoId(Long videoId);
}
