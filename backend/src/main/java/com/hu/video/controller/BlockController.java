package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.service.IBlockService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("block")
public class BlockController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IBlockService blockService;

    @RequestMapping("blockOn")
    public MsgResponse focusOn(Long userId, Long blockedId) {
        if (userId!=null&&blockedId!=null){
            return MsgResponse.success(blockService.addBlockd(userId,blockedId),null);
        }
        return MsgResponse.fail("参数错误");
    }


    @RequestMapping("deleteBlock")
    @ResponseBody
    public MsgResponse deleteBlock(HttpSession session, Long blockedId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null) {

            System.out.println("用户的ID"+user.getUserId()+"被屏蔽的ID"+blockedId);

            return MsgResponse.success(blockService.DeleteBlockedById(user.getUserId(),blockedId),null);
        }else {

            return MsgResponse.fail("用户未登录或关注用户ID错误");
        }
    }
    //判断为空
    @RequestMapping("verifyIfNUll")
    @ResponseBody
    public Integer verifyIfNUll(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        return blockService.selectCount(user.getUserId());
    }

    @RequestMapping("blockList")
    public MsgResponse blockList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user!=null) {
            Long userId = user.getUserId();
            List<Long> userBlockList = blockService.getUserBlocksList(userId);
            List<TUser> blockList= new ArrayList<TUser>();
            for(Long blocksId : userBlockList) {
                blockList.add(userService.getUserByUserId(blocksId));
            }
            return MsgResponse.success("success", blockList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }
    //要反过来，因为要知道谁屏蔽了自己
    @RequestMapping("getuserList")
    public List<TUser> getblockList(Long userId){
        List<Long> userBlockList = blockService.getUserList(userId);
        List<TUser> blockList= new ArrayList<TUser>();
        for(Long blocksId : userBlockList) {
            blockList.add(userService.getUserByUserId(blocksId));
        }
        System.out.println("getblockList"+blockList);
        return blockList;
    }

}
