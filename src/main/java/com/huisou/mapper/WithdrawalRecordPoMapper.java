package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.UniversalPo;
import com.huisou.po.WithdrawalRecordPo;

public interface WithdrawalRecordPoMapper {
    int deleteByPrimaryKey(Integer withdrawalRecordId);

    int insert(WithdrawalRecordPo record);

    int insertSelective(WithdrawalRecordPo record);

    WithdrawalRecordPo selectByPrimaryKey(Integer withdrawalRecordId);

    int updateByPrimaryKeySelective(WithdrawalRecordPo record);

    int updateByPrimaryKey(WithdrawalRecordPo record);

	List<WithdrawalRecordPo> findAllByStatus(@Param("userId")int userId,@Param("status")Integer status);

	List<UniversalPo> showVipWithdrawRecord(@Param("username")String username,@Param("phone")String phone,@Param("status")Integer status);

	void withdrawOpeartion(@Param("recordId")Integer recordId,@Param("status")Integer status);

	List<UniversalPo> listByRecords(@Param("recordIds")List<Integer> recordIds);

}