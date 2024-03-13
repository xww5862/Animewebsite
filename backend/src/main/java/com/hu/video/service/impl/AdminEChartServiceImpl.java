package com.hu.video.service.impl;

import com.hu.video.mapper.AdminMapper;
import com.hu.video.service.IAdminEChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEChartServiceImpl implements IAdminEChartService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<String> getFilmData() {
        return adminMapper.getFilmData();
    }

    @Override
    public List<Integer> getData() {
        List<Integer> list = adminMapper.getData();
        List<Float> floats = adminMapper.getPre();
        for (int i = 0; i < floats.size(); i++) {
            Integer a;
                 //如果评分的平均分大于2.5 预测就要比实际高，采用加法
            if (floats.get(i)>2.5){
                a = (int)Math.round(list.get(i) + list.get(i) * floats.get(i) * 0.6);
            }else {
                //如果评分的平均分小于2.5 预测就要比实际低，采用减法
                a = (int)Math.round(list.get(i) - list.get(i) * floats.get(i) * 0.4);
            }
            list.add(a);
        }
        return list;
    }

}
