package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.service.IFocusService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("focus")
public class FocusController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFocusService focusService;

    @RequestMapping(value = "focusVerify", method = RequestMethod.POST)
    public MsgResponse focusVerify(HttpSession session, String focusedId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long userId = user.getUserId();
            Long focusedIdLong = Long.parseLong(focusedId);
            return MsgResponse.success(focusService.focusVerify(userId, focusedIdLong), null);
        }
        return MsgResponse.fail("参数错误");
    }

    @RequestMapping("focusOn")
    public MsgResponse focusOn(HttpSession session, String focusedId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long userId = user.getUserId();
            Long focusedIdLong = Long.parseLong(focusedId);
            return MsgResponse.success(focusService.addFocused(userId, focusedIdLong),null);
        }
        return MsgResponse.fail("参数错误");
    }

    @RequestMapping("focusedList")
    public MsgResponse focusedList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user!=null) {
            Long userId = user.getUserId();
            List<Long> userFocusList = focusService.getUserFocusList(userId);
            List<TUser> focusList= new ArrayList<TUser>();
            for(Long focusId : userFocusList) {
                focusList.add(userService.getUserByUserId(focusId));
            }

            return MsgResponse.success("success", focusList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }

    @RequestMapping("fanList")
    public MsgResponse fanList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user!=null) {
            Long userId = user.getUserId();
            List<Long> userFansList = focusService.getUserFansList(userId);
            List<TUser> fansList= new ArrayList<TUser>();
            for(Long fansId : userFansList) {
                fansList.add(userService.getUserByUserId(fansId));
            }
            System.out.println("fansList"+fansList);
            return MsgResponse.success("success", fansList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }
    @RequestMapping("deleteFocus")
    @ResponseBody
    public MsgResponse deleteCollection(HttpSession session, Long focusedId) {

        TUser user = (TUser) session.getAttribute("user");
        if(user != null) {
            return MsgResponse.success(focusService.DeleteFocusedById(user.getUserId(),focusedId),null);
        }else {
            return MsgResponse.fail("用户未登录或关注用户ID错误");
        }
    }
    //判断为空
    @RequestMapping("verifyIfNUll")
    @ResponseBody
    public Integer verifyIfNUll(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        return focusService.selectCount(user.getUserId());
    }
    //粉丝+1
    @RequestMapping("fensiup")
    @ResponseBody
    public MsgResponse thumbsUp(HttpSession session, @RequestParam String focusedId) {
        TUser user = (TUser) session.getAttribute("user");

        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long focusedIdLong = Long.parseLong(focusedId);

            return MsgResponse.success(focusService.fensiUp(focusedIdLong),null);
        }
        return MsgResponse.fail("参数错误");
    }

    //粉丝-1
    @RequestMapping("fensidown")
    @ResponseBody
    public MsgResponse thumbsDown(HttpSession session, @RequestParam String focusedId) {
        TUser user = (TUser) session.getAttribute("user");

        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long focusedIdLong = Long.parseLong(focusedId);

            return MsgResponse.success(focusService.fensDown(focusedIdLong),null);
        }
        return MsgResponse.fail("参数错误");
    }
}
