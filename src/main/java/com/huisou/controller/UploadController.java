package com.huisou.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.FtpFileUtil;
import com.common.ResUtils;
import com.common.UploadUtils;

@RequestMapping(value = "/upload")
@RestController
public class UploadController {
	
	@Value(value="${local.baseUrl}")
	private String localBaseUrl;
	@Value(value="${ftp.baseUrl}")
	private String ftpBaseUrl;
	
	@Value(value="${vedio.dir}")
	private String vedioDir;
	@Value(value="${audio.dir}")
	private String audioDir;
	@Value(value="${resource.dir}")
	private String resourceDir;
	@Value(value="${img.dir}")
	private String imgDir;
	
	
	
	@RequestMapping(value = "/uploadVideo", method = RequestMethod.POST)
	public String uploadVideo(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		if (!file.isEmpty()) {
			// 保存图片到对应的服务器中去
			String vedioAddress = localBaseUrl+vedioDir+"/";
			String imagename = UploadUtils.uploadimage(file, vedioAddress);
//			if (imagename.startsWith("70") && imagename.length() < 4) {
//				return ResUtils.execRes(imagename);
//			}
			
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(vedioAddress+imagename);
				FtpFileUtil.uploadFile(imagename,inputStream,vedioDir);
			} catch (FileNotFoundException e) {
				try {
					if(null!=inputStream){
						inputStream.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String vedioFtp = ftpBaseUrl+vedioDir+"/";
			map.put("resUrl", vedioFtp+imagename);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}
	}
	
	@RequestMapping(value = "/uploadAudio", method = RequestMethod.POST)
	public String uploadAudio(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		if (!file.isEmpty()) {
			// 保存图片到对应的服务器中去
			String audioAddress = localBaseUrl+audioDir+"/";
			String imagename = UploadUtils.uploadimage(file, audioAddress);
//			if (imagename.startsWith("70") && imagename.length() < 4) {
//				return ResUtils.execRes(imagename);
//			}
			
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(audioAddress+imagename);
				FtpFileUtil.uploadFile(imagename,inputStream,audioDir);
			} catch (FileNotFoundException e) {
				try {
					if(null!=inputStream){
						inputStream.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String audioFtp = ftpBaseUrl+audioDir+"/";
			map.put("resUrl", audioFtp+imagename);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}
	}
	
	@RequestMapping(value = "/uploadResource", method = RequestMethod.POST)
	public String uploadResource(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		if (!file.isEmpty()) {
			// 保存图片到对应的服务器中去
			String resourceAddress = localBaseUrl+resourceDir+"/";
			String imagename = UploadUtils.uploadimage(file, resourceAddress);
//			if (imagename.startsWith("70") && imagename.length() < 4) {
//				return ResUtils.execRes(imagename);
//			}
			
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(resourceAddress+imagename);
				FtpFileUtil.uploadFile(imagename,inputStream,resourceDir);
			} catch (FileNotFoundException e) {
				try {
					if(null!=inputStream){
						inputStream.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String resourceFtp = ftpBaseUrl+resourceDir+"/";
			map.put("resUrl", resourceFtp+imagename);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}
	}
	
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		if (!file.isEmpty()) {
			// 保存图片到对应的服务器中去
			String imgAddress = localBaseUrl+imgDir+"/";
			String imagename = UploadUtils.uploadimage(file, imgAddress);
//			if (imagename.startsWith("70") && imagename.length() < 4) {
//				return ResUtils.execRes(imagename);
//			}
			
			InputStream inputStream = null;
			try {
				inputStream = new FileInputStream(imgAddress+imagename);
				FtpFileUtil.uploadFile(imagename,inputStream,imgDir);
			} catch (FileNotFoundException e) {
				try {
					if(null!=inputStream){
						inputStream.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 访问图片的路径
			String imgFtp = ftpBaseUrl+imgDir+"/";
			map.put("resUrl", imgFtp+imagename);
			return ResUtils.okRes(map);
		} else {
			return ResUtils.errRes("102", "文件不能为空");
		}
	}
}
