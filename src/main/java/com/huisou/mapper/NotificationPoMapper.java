package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.NotificationPo;

public interface NotificationPoMapper {
    int deleteByPrimaryKey(Integer notificationId);

    int insert(NotificationPo record);

    int insertSelective(NotificationPo record);

    NotificationPo selectByPrimaryKey(Integer notificationId);

    int updateByPrimaryKeySelective(NotificationPo record);

    int updateByPrimaryKey(NotificationPo record);

	List<NotificationPo> findAll(@Param(value="userId")Integer userId);
}