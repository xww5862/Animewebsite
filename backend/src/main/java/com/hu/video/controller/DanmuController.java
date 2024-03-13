package com.hu.video.controller;

import com.hu.video.entity.Danmu;
import com.hu.video.service.IDanmuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/danmu")
public class DanmuController {
    @Resource
    private IDanmuService danmuService;

    @PostMapping("/insertDanmu")
    public int insertDanmu(Danmu danmu){
        int i = danmuService.save(danmu);
        return i;
    }
    @GetMapping("/countDanmu")
    public int countDanmu(Long videoId){
        int count = countDanmu(videoId);
        return count;
    }
    @GetMapping("/selectAllByVideoIdDanmu")
    public List<Danmu> selectAllByVideoIdDanmu(Long videoId){
        List<Danmu> danmus = danmuService.selectAllByVideoId(videoId);
        return danmus;
    }
}
