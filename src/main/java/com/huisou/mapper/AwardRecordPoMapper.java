package com.huisou.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.AwardRecordPo;
import com.huisou.vo.AwardRecordVo;

public interface AwardRecordPoMapper {
    int deleteByPrimaryKey(Integer awardRecordId);

    int insert(AwardRecordPo record);

    int insertSelective(AwardRecordPo record);

    AwardRecordPo selectByPrimaryKey(Integer awardRecordId);

    int updateByPrimaryKeySelective(AwardRecordPo record);

    int updateByPrimaryKey(AwardRecordPo record);
    List<AwardRecordVo> search(@Param(value="resId")Integer resId, @Param(value="resType")String resType);

	List<AwardRecordVo> findAll(@Param(value="nickname")String nickname, @Param(value="phone")String phone, @Param(value="startDate")Date startDate, @Param(value="endDate")Date endDate, @Param(value="awardStatus")String awardStatus);

	AwardRecordPo findOne(@Param(value="awardNo")String awardNo);
}