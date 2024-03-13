package com.hu.video.service.impl;

import com.hu.video.entity.TFocus;
import com.hu.video.entity.TUser;
import com.hu.video.mapper.TFocusMapper;
import com.hu.video.service.IFocusService;
import com.hu.video.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusServiceImpl implements IFocusService {

    @Autowired
    private TFocusMapper focusMapper;

    @Autowired
    private IUserService userService;

    @Override
    public String focusVerify(Long userId, Long focusedIdLong) {
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0)
            return "未关注";
        return "已关注";
    }

    @Override
    public List<Long> getUserFocusList(Long userId) {
        return focusMapper.selectFocusedsId(userId);
    }

    @Override
    public List<Long> getUserFansList(Long focusedIdLong) {
        return focusMapper.selectFansId(focusedIdLong);
    }

    @Override
    public int selectCount(Long userId) {
        return focusMapper.selectCount(userId);
    }

    @Override
    public String addFocused(Long userId, Long focusedIdLong) {
        //不可对自己关注
        if(userId.equals(focusedIdLong)) {
            return "不可对自己进行关注";
        }
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusedId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0) {
            try {
                focusMapper.insertSelective(focus);
                return "关注成功";
            } catch (Exception e) {
                return "关注失败";
            }
        }
        return "已关注";
    }

    @Override
    public String fensiUp(Long focusedIdLong) {
        int flag = focusMapper.updateUserFanSumByfocusedIdId(focusedIdLong);
        if (flag!=0){
            return "粉丝加一成功";
        }else {
            return "粉丝加一失败";
        }
    }

    @Override
    public String fensDown(Long focusedIdLong) {
        int flag = focusMapper.updateUserFanSubByfocusedIdId(focusedIdLong);
        if (flag!=0){
            return "粉丝减一成功";
        }else {
            return "粉丝减一失败";
        }
    }

    @Override
    public String DeleteFocusedById(Long userId, Long focusedIdLong) {

        if (userService.getUserByUserId(userId)!=null){
            try{
                if (focusMapper.selectOne(userId,focusedIdLong)!=null){
                    focusMapper.deleteFocusByUserId(userId,focusedIdLong);
                    return "关注删除成功";
                }else {
                    return "关注记录不存在";
                }
            }catch (Exception e){
                e.printStackTrace();
                return "关注删除失败";
            }
        }
        return "用户未登录";
    }
}
