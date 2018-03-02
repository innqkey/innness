package com.huisou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huisou.po.NoticeCertificatePo;

public interface NoticeCertificatePoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeCertificatePo record);

    int insertSelective(NoticeCertificatePo record);

    NoticeCertificatePo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeCertificatePo record);

    int updateByPrimaryKey(NoticeCertificatePo record);

	NoticeCertificatePo find(@Param("type")String type);

	List<NoticeCertificatePo> findType(@Param("type")String type);
}