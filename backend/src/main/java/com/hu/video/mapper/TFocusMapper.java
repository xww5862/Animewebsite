package com.hu.video.mapper;

import com.hu.video.entity.TFocus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TFocusMapper {
    int deleteByPrimaryKey(Long focusId);

    int insert(TFocus record);

    int insertSelective(TFocus record);

    TFocus selectByPrimaryKey(Long focusId);

    TFocus selectOne(@Param("userId") Long userId, @Param("focusedId") Long focusedId);

    int deleteFocusByUserId(@Param("userId") Long userId, @Param("focusedId") Long focusedId);

    int updateByPrimaryKeySelective(TFocus record);

    int updateByPrimaryKey(TFocus record);

    int selectOneVerify(TFocus focus);
    //粉丝+1
    @Update("update t_user set fan_num = fan_num + 1 where  user_id = #{focusedId}")
    int updateUserFanSumByfocusedIdId(@Param("focusedId") Long focusedId);
    //粉丝-1
    @Update("update t_user set fan_num = fan_num - 1 where  user_id = #{focusedId}")
    int updateUserFanSubByfocusedIdId(@Param("focusedId") Long focusedId);

    List<Long> selectFocusedsId(Long userId);

    List<Long> selectFansId(Long focusedId);

    int selectCount(Long userId);
}
