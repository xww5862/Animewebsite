package com.hu.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.service.IRecordService;
import com.hu.video.service.IVideoService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("record")
@RestController
public class RecordController {
    @Autowired
    private IRecordService recordService;

    @RequestMapping("removeRecord")
    public MsgResponse removeRecord(Long videoId, HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if (user != null) {
            return MsgResponse.success(recordService.deleteRecordById(user.getUserId(),videoId), null);
        }
        return MsgResponse.fail("删除失败");
    }

    @RequestMapping("getAllRecord")
    public MsgResponse getAllRecord(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null) {
            List<TVideo> records = recordService.getAllRecord(user.getUserId());

            if(records != null) {
                return MsgResponse.success("获取记录列表成功", records);
            }
            else {
                return MsgResponse.fail("获取记录列表失败");
            }
        }else {
            return MsgResponse.fail("当前用户不存在，请登录！");
        }
    }

    //判断为空
    @RequestMapping("verifyIfNUll")
    @ResponseBody
    public Integer verifyIfNUll(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        return recordService.selectCount(user.getUserId());
    }


    //分页视频
    @RequestMapping("videoPageInfo")
    @ResponseBody
    public PageInfo<TVideo> getPageInfo(int pageNum, int pageSize,HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<TVideo> videoInfoDTOs =  recordService.getAllRecord(user.getUserId());

        //获得 视频分页
        PageInfo<TVideo> vidoePageInfo = new PageInfo<TVideo>(videoInfoDTOs);

        return vidoePageInfo;
    }
}
