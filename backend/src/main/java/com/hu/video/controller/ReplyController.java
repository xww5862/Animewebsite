package com.hu.video.controller;

import com.hu.video.entity.Reply;
import com.hu.video.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("reply")
public class ReplyController {
    @Autowired
    private IReplyService replyService;

    @PostMapping("addReplyComment")
    public int addReplyComment(Reply reply){
        Reply reply1 = new Reply();
        reply1.setVideoId(reply.getVideoId());
        reply1.setCommentId(reply.getCommentId());
        reply1.setReplyUserId(reply.getReplyUserId());
        reply1.setRepliedUserId(reply.getRepliedUserId());
        reply1.setContent(reply.getContent());

        long s=new Date().getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date(s);
        reply1.setCreateTime(date);
        System.out.println(reply1+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return replyService.addReplyComment(reply1);

    }


}
