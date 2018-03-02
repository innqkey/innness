package com.huisou.mapper;

import java.util.List;
import java.util.Map;
import com.huisou.po.VisitRecordPo;
import com.huisou.vo.VisitRecordVo;

public interface VisitRecordPoMapper {
    int deleteByPrimaryKey(Integer visitId);

    int insert(VisitRecordPo record);

    int insertSelective(VisitRecordPo record);

    VisitRecordPo selectByPrimaryKey(Integer visitId);

    int updateByPrimaryKeySelective(VisitRecordPo record);

    int updateByPrimaryKey(VisitRecordPo record);

	List<VisitRecordVo> select(Map<String, String> maps);

}