package com.hu.video.controller;


import cn.hutool.core.collection.CollUtil;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.mapper.TVideotypeMapper;
import com.hu.video.service.EchartService;
import com.hu.video.service.IUserService;
import com.hu.video.service.IVideoService;
import com.hu.video.util.MsgResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("echart")
public class EchartController {
    @Resource
    private EchartService echartService;
    @Resource
    private TVideotypeMapper tVideotypeMapper;
    @Resource
    private IVideoService videoService;
    @Resource
    private IUserService userService;

    @GetMapping("/getAllVideoType")
    public MsgResponse getAllVideoType(){
        List<TVideo> videotypeList = videoService.getAllVideo();
        int b1=0;
        int b2=0;
        int b3=0;
        int b4=0;
        int b5=0;
        int b6=0;
        for(TVideo videotype : videotypeList){
            long TypeNameId =videotype.getVideoTypeId();
            if (TypeNameId == 1){
                b1++;
            }
            if (TypeNameId == 2){
                b2++;
            }
            if (TypeNameId == 3){
                b3++;
            }
            if (TypeNameId == 4){
                b4++;
            }
            if (TypeNameId == 5){
                b5++;
            }
            if (TypeNameId == 6){
                b6++;
            }

        }
        return MsgResponse.success("成功导入视频类型",CollUtil.newArrayList(b1,b2,b3,b4,b5,b6));

    }

    @GetMapping("/getAllUserType")
    public MsgResponse getAllUserType(){
        List<TUser> userList = userService.getAllUser();
        int b1=0;
        int b2=0;
        int b3=0;
        for(TUser userType : userList){
            long userTypeId = userType.getStateId();
            if (userTypeId == 1){
                b1++;
            }
            if (userTypeId == 2){
                b2++;
            }
            if (userTypeId == 3){
                b3++;
            }
        }
        return MsgResponse.success("成功导入用户类型",CollUtil.newArrayList(b1,b2,b3));

    }

    @GetMapping("/getVideoPPnum")
    public MsgResponse getVideoPPnum(){
        List<TVideo> videoList = videoService.getVideoAndZanNum();
        int b1=0;
        int b2=0;
        int b3=0;
        int b4=0;
        int b5=0;
        b1 = videoList.get(0).getPpNum();
        b2 = videoList.get(1).getPpNum();
        b3 = videoList.get(2).getPpNum();
        b4 = videoList.get(3).getPpNum();
        b5 = videoList.get(4).getPpNum();

        return MsgResponse.success("成功导入视频点赞",CollUtil.newArrayList(b1,b2,b3,b4,b5));
    }


    @GetMapping("/getVideoName")
    public MsgResponse getVideoName(){
        List<TVideo> videoList = videoService.getVideoAndZanNum();
        String b1 = "";
        String b2 = "";
        String b3 = "";
        String b4 = "";
        String b5 = "";

        b1 = videoList.get(0).getVideoTitle();
        b2 = videoList.get(1).getVideoTitle();
        b3 = videoList.get(2).getVideoTitle();
        b4 = videoList.get(3).getVideoTitle();
        b5 = videoList.get(4).getVideoTitle();

        return MsgResponse.success("成功导入视频名称",CollUtil.newArrayList(b1,b2,b3,b4,b5));

    }

    @GetMapping("/getUserName")
    public MsgResponse getUserName(){
        List<TUser> userList = userService.getUserAndFan();
        String b1 = "";
        String b2 = "";
        String b3 = "";
        String b4 = "";
        String b5 = "";

        b1 = userList.get(0).getUserName();
        b2 = userList.get(1).getUserName();
        b3 = userList.get(2).getUserName();
        b4 = userList.get(3).getUserName();
        b5 = userList.get(4).getUserName();

        return MsgResponse.success("成功导入用户名称",CollUtil.newArrayList(b1,b2,b3,b4,b5));
    }

    @GetMapping("/getUserFannum")
    public MsgResponse getUserFannum(){
        List<TUser> userList = userService.getUserAndFan();
        int b1=0;
        int b2=0;
        int b3=0;
        int b4=0;
        int b5=0;
        b1 = userList.get(0).getFanNum();
        b2 = userList.get(1).getFanNum();
        b3 = userList.get(2).getFanNum();
        b4 = userList.get(3).getFanNum();
        b5 = userList.get(4).getFanNum();

        return MsgResponse.success("成功导入用户粉丝数量",CollUtil.newArrayList(b1,b2,b3,b4,b5));
    }
}
