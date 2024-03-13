package com.hu.video.mapper;

import com.hu.video.entity.TBlock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TBlockMapper {
    int insert(TBlock record);

    int DeleteBlockedById(@Param("userId")Long userId, @Param("blockedId")Long blockedId);

    List<Long> selectBlocksId(Long userId);

    List<Long> selectUserId(Long blockedId);

    int selectOneVerify(TBlock block);

    int selectCount(Long userId);
}
