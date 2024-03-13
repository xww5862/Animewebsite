package com.hu.video.service;

import com.hu.video.entity.TUser;

import java.util.List;

public interface IFocusService {
    String focusVerify(Long userId, Long focusedIdLong);

    String addFocused(Long userId, Long focusedIdLong);

    String fensiUp( Long focusedIdLong);

    String fensDown( Long focusedIdLong);

    String DeleteFocusedById(Long userId, Long focusedIdLong);

    List<Long> getUserFocusList(Long userId);

    List<Long> getUserFansList(Long focusedIdLong);

    int selectCount(Long userId);
}
