package com.huisou.mapper;

import com.huisou.po.ManagerPo;

public interface ManagerPoMapper {
    int deleteByPrimaryKey(Integer managerId);

    int insert(ManagerPo record);

    int insertSelective(ManagerPo record);

    ManagerPo selectByPrimaryKey(Integer managerId);

    int updateByPrimaryKeySelective(ManagerPo record);

    int updateByPrimaryKey(ManagerPo record);
}