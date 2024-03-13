package com.hu.video.service;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;

import java.util.List;

public interface IUserService {
    ResultDTO<TUser> login(String userTel, String password);

    List<TUser> getUserAndFan();

    List<TUser> getAllUser();

    boolean queryByuserTel(String userTel);

    ResultDTO register(TUser tUser);

    void updateUserByUserId(TUser tUser);

    TUser getUserByUserId(Long userId);

    TUser getNowStateId(Long userId);

    int updateUserIcon(String s, Long userId);

    int updateuserinfovip(Long userId);

    TUser getUserByUserIphone(String userTel);
}
