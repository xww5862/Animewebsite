package com.hu.video.service.impl;

import com.hu.video.entity.TVideotype;
import com.hu.video.mapper.EchartMapper;
import com.hu.video.service.EchartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EchartServiceImpl implements EchartService {
    @Resource
    private EchartMapper echartMapper;
    @Override
    public List<TVideotype> selectAllVideoType() {
        return echartMapper.selectAllVideoType();
    }
}
