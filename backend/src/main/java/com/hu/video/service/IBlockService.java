package com.hu.video.service;

import java.util.List;

public interface IBlockService {
    String addBlockd(Long userId, Long blockedIdLong);

    String DeleteBlockedById(Long userId, Long blockedId);

    List<Long> getUserBlocksList(Long userId);

    List<Long> getUserList(Long blockedId);

    int selectCount(Long userId);
}
