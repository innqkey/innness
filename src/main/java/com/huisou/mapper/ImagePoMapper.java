package com.huisou.mapper;

import java.util.List;

import com.huisou.po.ImagePo;

public interface ImagePoMapper {
    int deleteByPrimaryKey(Integer imageId);

    int insert(ImagePo record);

    int insertSelective(ImagePo record);

    ImagePo selectByPrimaryKey(Integer imageId);

    int updateByPrimaryKeySelective(ImagePo record);

    int updateByPrimaryKey(ImagePo record);
    
	List<ImagePo> getByOpenId(String openId);
	/**
	 * 删除
	 */
	void deleteByImageType();
	/**
	 * 获取用户的图片
	 */
	List<ImagePo> getBackageImage();

	void changeStatus(Integer imageId);

	List<ImagePo> getImageByOpenIdAndDelete(String openId);
	
}