package com.hu.video.controller;


import com.hu.video.constant.DPlayerConstants;
import com.hu.video.entity.Danmu;
import com.hu.video.entity.TUser;
import com.hu.video.service.IDanmuService;
import com.hu.video.socketserver.BarrageWebSocket;
import com.hu.video.util.MapperUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("barrage")
public class BarrageController {
    @Autowired
    private IDanmuService danmuService;

    @ResponseBody
    @RequestMapping(value = "v3", method = RequestMethod.GET)
    public String getv3(@RequestParam String id) throws Exception {
        Map map = new HashMap();
        Long viodeId = Long.parseLong(id);
        int count = danmuService.count(viodeId);
        List data = new ArrayList();
        List<Danmu> danmus = danmuService.selectAllByVideoId(viodeId);
        for (int i = 0; i < count; i++) {
            Danmu danmu_temp = danmus.get(i);
            List d = new ArrayList();
                d.add(danmu_temp.getTime());
                d.add(danmu_temp.getType());
                d.add(danmu_temp.getColor());
                d.add(danmu_temp.getAuthor());
                d.add(danmu_temp.getText());
            data.add(d);
        }
        map.put("code", DPlayerConstants.DPLAYER_SUCCESS_CODE);
        map.put("data",data);

        return MapperUtils.obj2json(map);
    }

    @ResponseBody
    @RequestMapping(value = "v3", method = RequestMethod.POST)
    public String postv3(@RequestBody Map<String,String> param, HttpServletRequest request) throws Exception {

        Map map = new HashMap();
        Danmu danmu = new Danmu();
        Double time = Double.valueOf(param.get("time"));
        Integer type = Integer.valueOf(param.get("type"));
        Long color = Long.valueOf(param.get("color"));
        Long author = Long.valueOf(param.get("author"));
        String text = param.get("text");
        Long videoId = Long.valueOf(param.get("id"));
        danmu.setTime(time);
        danmu.setType(type);
        danmu.setColor(color);
        danmu.setAuthor(author);
        danmu.setText(text);
        danmu.setVideoId(videoId);
        int res = danmuService.save(danmu);
        map.put("code", DPlayerConstants.DPLAYER_SUCCESS_CODE);
        map.put("data",param);

        return MapperUtils.obj2json(map);
    }




    @RequestMapping("sendGroupMessage/{videoId}")
    public Map<String, Object> groupMessage(HttpSession session, @PathVariable Integer videoId, @RequestParam String message) {
        Map<String, Object> res = new HashMap<>();
        TUser sendUser = (TUser) session.getAttribute("user");
        if (sendUser != null && !message.equals("")) {
            // 接受消息message 进行组发
            BarrageWebSocket.sendGroupMessage(sendUser.getUserName(), videoId, message);
            res.put("code", 200);
            res.put("msg", "success");
        }
        res.put("code", 500);
        res.put("msg", "error");
        return res;
    }

}
