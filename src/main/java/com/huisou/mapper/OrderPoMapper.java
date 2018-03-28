package com.huisou.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.OrderPo;
import com.huisou.vo.OrderVo;

public interface OrderPoMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderPo record);

    int insertSelective(OrderPo record);

    OrderPo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderPo record);

    int updateByPrimaryKey(OrderPo record);
    
    List<OrderVo> orderCourseList(Map map);
    
    List<OrderVo> orderVideoAudioList(Map map);

    List<OrderPo> findByUserIdAndResId(@Param(value="userId")Integer userId,@Param(value="resId")Integer resId,@Param(value="resType")String resType,@Param(value="payStatus")String payStatus);
    
    List<OrderPo> findByMap(Map map);

	Integer getSuccessCount(Map<String, String> maps);

	OrderPo findByTradeNo(String tradeNo);
	
	int findPayAllMoney(@Param(value="userId")Integer userId);
}