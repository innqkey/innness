package com.huisou.service;

import java.util.List;

import com.huisou.po.ImagePo;

/**
* 类说明：
* @author 
* @version 创建时间：2018年2月7日 下午7:42:38
* 
*/
public interface ImageService {

	void saveImage(ImagePo imagePo);

	ImagePo getImageByOpenId(String openId);

	void deleteImage(ImagePo image);


	void deleteBackageImage();
	public ImagePo getBackageImage();

	List<ImagePo> getImageByOpenIdAndDelete(String openId);
	
	
}
