package com.huisou.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huisou.mapper.ImagePoMapper;
import com.huisou.po.ImagePo;
import com.huisou.service.ImageService;

/**
* 类说明： 用于生成图片分享的
* @author 
* @version 创建时间：2018年2月7日 下午7:43:01
* 
*/
@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImagePoMapper imagePoMapper;

	@Override
	public void saveImage(ImagePo imagePo) {
		imagePoMapper.insertSelective(imagePo);
		
	}
	
	@Override
	public ImagePo getImageByOpenId(String openId) {
		List<ImagePo> list = imagePoMapper.getByOpenId(openId);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void deleteImage(ImagePo image) {
		imagePoMapper.deleteByPrimaryKey(image.getImageId());
		
	}
	/**
	 * 删除图片类型为2的图片，
	 * 这是用来在上传图片前删除之前的图片记录
	 */
	@Override
	public void deleteBackageImage() {
		imagePoMapper.deleteByImageType();
		
	}
	/**
	 * 获取背景的图片
	 */
	public ImagePo getBackageImage() {
		List <ImagePo> list =  imagePoMapper.getBackageImage();
		if (list != null && list.size() > 0) {
			if (list.size() > 1) {
				for (int i = 1; i < list.size() -1; i++) {
					imagePoMapper.deleteByPrimaryKey(list.get(i).getImageId());
				}
			}
			return list.get(0);
		}
		return null;
	}
	
}
