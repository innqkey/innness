package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.ChatMessagePo;

public interface ChatMessagePoMapper {
    int deleteByPrimaryKey(Long messageid);

    int insert(ChatMessagePo record);

    int insertSelective(ChatMessagePo record);

    ChatMessagePo selectByPrimaryKey(Long messageid);

    int updateByPrimaryKeySelective(ChatMessagePo record);

    int updateByPrimaryKeyWithBLOBs(ChatMessagePo record);

    int updateByPrimaryKey(ChatMessagePo record);

	List<ChatMessagePo> listGetMessage(@Param("fromUser")Integer fromUser, @Param("toUser")Integer toUser,@Param("messageid") Long messageid);
}