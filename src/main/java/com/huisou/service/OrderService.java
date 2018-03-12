package com.huisou.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.huisou.po.OrderPo;
import com.huisou.po.RegistPo;
import com.huisou.vo.OrderVo;
import com.huisou.vo.PageTemp;

public interface OrderService {

	PageInfo<OrderVo> orderCourseList(Map paraMap,PageTemp pageTemp);
	
	PageInfo<OrderVo> orderVideoAudioList(Map paraMap,PageTemp pageTemp);
	
	/**
	 * 根据用户id和资源类型以及资源id查询实付付款成功
	 * @param userId 用户id
	 * @param resId 资源id
	 * @param resType 资源类型
	 * @return true:支付过;false:未支付或支付失败
	 */
	boolean IsPay(Integer userId, Integer resId , String resType);

	List<OrderPo> findOrderNoPay(Integer userId, Integer resId , String resType);
	
	/**
	 * 根据参数获取order列表
	 * @param map
	 * @return
	 */
	List<OrderVo> findOrderByParamers(Map map);

	OrderPo selectOne(Integer orderId);

	void update(OrderPo orderPo);

	OrderPo registCourse(Integer userId, Integer courseId, String phone, List<RegistPo> list);

	Integer add(OrderPo orderPo);

	OrderPo findByoutTradeNo(String outTradeNo);

}
