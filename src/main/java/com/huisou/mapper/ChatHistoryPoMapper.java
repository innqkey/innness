package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.ChatHistoryPo;
import com.huisou.po.ChatRecordVo;

public interface ChatHistoryPoMapper {
    int deleteByPrimaryKey(Integer chatHistoryId);

    int insert(ChatHistoryPo record);

    int insertSelective(ChatHistoryPo record);

    ChatHistoryPo selectByPrimaryKey(Integer chatHistoryId);

    int updateByPrimaryKeySelective(ChatHistoryPo record);

    int updateByPrimaryKey(ChatHistoryPo record);

	List<ChatRecordVo> getChatRecord(@Param("userId")String userId);

	void updateStatus(Integer historyId);

}