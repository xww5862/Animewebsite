package com.hu.video.service.impl;

import com.hu.video.entity.TBlock;
import com.hu.video.mapper.TBlockMapper;
import com.hu.video.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockServiceImpl implements IBlockService {
    @Autowired
    private TBlockMapper blockMapper;
    @Override
    public String addBlockd(Long userId, Long blockedIdLong) {
        TBlock block = new TBlock();
        block.setUserId(userId);
        block.setBlockedId(blockedIdLong);
        int flag = blockMapper.selectOneVerify(block);
        if (flag==0){
            try {
                blockMapper.insert(block);
                return "屏蔽成功";
            } catch (Exception e) {
                return "屏蔽失败";
            }
        }
        return "已屏蔽";
    }

    @Override
    public String DeleteBlockedById(Long userId, Long blockedId) {
       int flag = blockMapper.DeleteBlockedById(userId,blockedId);
        if (flag!=0){
            return "删除屏蔽成功";
        }else {
            return "删除屏蔽失败";
        }
    }

    @Override
    public List<Long> getUserBlocksList(Long userId) {
        return blockMapper.selectBlocksId(userId);
    }

    @Override
    public List<Long> getUserList(Long blockedId) {
        return blockMapper.selectUserId(blockedId);
    }

    @Override
    public int selectCount(Long userId) {
        return blockMapper.selectCount(userId);
    }
}
