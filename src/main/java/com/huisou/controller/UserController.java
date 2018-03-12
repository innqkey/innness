package com.huisou.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.DateUtils;
import com.common.MD5Util;
import com.common.QrcodeUtils;
import com.common.ResUtils;
import com.common.UploadUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.cache.RedisSmsCodeCache;
import com.huisou.cache.RedisUserTokenCache;
import com.huisou.constant.ContextConstant;
import com.huisou.po.ImagePo;
import com.huisou.po.IntegralRecordPo;
import com.huisou.po.NotificationPo;
import com.huisou.po.UserPo;
import com.huisou.service.ImageService;
import com.huisou.service.IntegralRecordService;
import com.huisou.service.NotificationService;
import com.huisou.service.UserService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.RegistVo;
import com.huisou.vo.UserVo;


/** 
* @author qinkai 
* @date 2018年1月31日
*/

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	@Autowired
	private IntegralRecordService integralRecordService;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RedisSmsCodeCache smsCache;
	@Autowired
	private NotificationService notificationService;
	@Value("${wechat.pay.appId}")
	public String appid;
	
	@Value("${wechat.mp.secret}")
	public String secret;
	
	
	@Value("${image.url}")
	public String saveUrl;
	

	@Value("${qrcode.startX}")
	public int qrcodestartX ;
	@Value("${qrcode.startY}")
	public int qrcodestartY;
	@Value("${qrcode.codeWidth}")
	public int codeWidth;
	@Value("${qrcode.codeHight}")
	public int codeHight;

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private RedisUserTokenCache userTokenCache;
	
	/**
	 * 用户管理列表/查找
	 * @param pageTemp
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,PageTemp pageTemp){
		try {
			Map para = super.getPara();
			PageInfo<UserPo> voList = userService.findAll(para,pageTemp);
			return ResUtils.okRes(voList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 删除一个用户
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(required = false, value = "userId[]") List<String> userId){
		if (null == userId || userId.size() <= 0){
			return ResUtils.errRes("404", "请求参数有误");
		}
		userService.deleteOne(userId);
		return ResUtils.okRes();
	}
	
	/**
	 * 查看用户详情
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Integer userId){
		if (null == userId || userId <= 0){
			return ResUtils.errRes("404", "请求参数有误");
		} else {
			UserPo user = userService.find(userId);
			return ResUtils.okRes(user);
		}
	}
	
	/**
	 * 关联一个微信用户
	 * @param requet
	 * @return
	 */
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String add(HttpServletRequest request, UserPo userPo){
		try {
			if (null != userPo){
				userPo.setAuthStatus("2");
				Integer userId = userService.addOne(userPo);
				return ResUtils.okRes(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 微信用户认证绑定
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authorize",method = RequestMethod.POST)
	public String authorize(HttpServletRequest request){
		try {
			String token = request.getParameter("userToken");
			String code = request.getParameter("code");
			if(StringUtils.isBlank(token) || StringUtils.isBlank(code)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			String redisCode = smsCache.getSmsCode(token);
			if(!code.equals(redisCode)){
				return ResUtils.errRes("106", "验证码错误");
			}
			Map para = super.getPara();
			if (super.getUserIdByToken(request.getParameter("userToken")) <= 0 || null == para.get("userName") || null == para.get("cardType")
					|| null == para.get("cardNum") || null == para.get("phone")){
				return ResUtils.errRes("404", "请求参数有误");
			}
			Integer userId = super.getUserIdByToken(request.getParameter("userToken"));
			String userName = (String) para.get("userName");
			String cardType = (String) para.get("cardType");
			String cardNum = (String) para.get("cardNum");
			String phone = (String) para.get("phone");
			UserPo userPo = userService.find(userId);
			userPo.setUsername(userName);
			userPo.setCardType(cardType);
			userPo.setCardNum(cardNum);
			userPo.setPhone(phone);;
			userPo.setAuthStatus("1");
			userPo.setIntegralNum(1000L);
			userService.updateOne(userPo);
			IntegralRecordPo integralRecordPo = new IntegralRecordPo();
			integralRecordPo.setCreateTime(new Date());
			integralRecordPo.setResPrice(1000L);
			integralRecordPo.setResType("2");
			integralRecordPo.setUserId(userId);
			integralRecordService.insertIntegralRecord(integralRecordPo);
			NotificationPo notificationPo = new NotificationPo();
			notificationPo.setUserId(userId);
			notificationPo.setOpenId(userPo.getOpenid());
			notificationPo.setNotificationType("RZ");
			notificationPo.setNotificationContext("用户绑定增加1000积分");
			notificationPo.setCreateTime(new Date());
			notificationService.addOne(notificationPo);
			return ResUtils.okRes(userPo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 获取同学
	 * @param request
	 * @return
	 */
	@RequestMapping("/getclassmate")
	public String getClassmate(HttpServletRequest request,PageTemp pageTemp){
		String  openId = super.getOpenIdByToken(request.getParameter("userToken"));
		PageInfo<UserVo> result = userService.findAllClassmate(openId,pageTemp);
		return ResUtils.okRes(result);
	}
	
	/**
	 * 用于用户的分享的背景图片的上传
	 */
	@RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
	public String uploadImg(@RequestParam("file") MultipartFile file,
			HttpServletRequest request){
		try {
			if (!file.isEmpty()) {
				// 保存图片到对应的服务器中去
				String imagename = UploadUtils.uploadimageReturnFileName(file, saveUrl);
				//删除之前的图片记录
				imageService.deleteBackageImage();
				ImagePo imagePo = new ImagePo();
				imagePo.setCreateTime(new Date());
				imagePo.setFileName(imagename);
				imagePo.setImageType(2);
				imageService.saveImage(imagePo);
				return ResUtils.okRes(imagename);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResUtils.execRes();
		}
		return ResUtils.execRes();
	}
	
	/**
	 * 返回上传背景图片的filename
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getBackgroundImage")
	public String getBackgroundImage(HttpServletRequest request){
		ImagePo backageImagePo = imageService.getBackageImage();
		if (backageImagePo != null){
			return ResUtils.okRes(backageImagePo.getFileName());
		} else {
			return ResUtils.okRes(null);
		}
	}
	
	/**
	 *  专门用于图片的上传内容的展示
	 * @param request
	 * @param dir
	 * @param imageName
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/displayImage/{imageName}")
	public String displayImage(HttpServletRequest request
			,@PathVariable("imageName")String imageName,HttpServletResponse response) {
		if (StringUtils.isBlank(imageName)) {
			return ResUtils.exceCode;
		}
		
		String requestURI = request.getRequestURI();
		String suffix = requestURI.substring(requestURI.lastIndexOf(".")+1);
		OutputStream outputStream = null;
		FileInputStream fileInputStream = null;
		try {
			StringBuilder url = new StringBuilder();
			url.append(saveUrl).append(imageName).append(".").append(suffix);
			// 度图片
			fileInputStream = new FileInputStream(url.toString());
			response.setContentType("image/png");
			outputStream = response.getOutputStream();
			int available = fileInputStream.available();
			byte[] data = new byte[available];
			fileInputStream.read(data);
			outputStream.write(data);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.exceCode;
		} finally {
			try {
				if (fileInputStream != null){
					fileInputStream.close();
				}
				if (outputStream != null){
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return ResUtils.exceCode;
			}
		}
		return ResUtils.okRes();
	}
	
	
	
	
	
	
	/**
	 * 用户传过来openid然后生成分享的二维码图片
	 * @param openId
	 * @return
	 */
	@RequestMapping("/getqrcode")
	public String getQrcode(HttpServletResponse response,HttpServletRequest request) {
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			String  openId = super.getOpenIdByToken(request.getParameter("userToken"));
			//判断是是否存在二维码，如果存在的话，直接调用
			if (StringUtils.isBlank(openId)) {
				return ResUtils.execRes();
			}
			
			ImagePo image = imageService.getImageByOpenId(openId);
			if (image!= null && StringUtils.isNotBlank(image.getFileName())) {
				//如果二维码保存时间大于30天的话，那么就删除
				Date addDate = DateUtils.addDate(image.getCreateTime(), 30);
				ImagePo backageImage = imageService.getBackageImage();
				boolean back = false;
				if (backageImage != null) {
					 back = !backageImage.getImageId().equals(image.getBackgroudId());
				}
				
				Boolean date = new Date().getTime() > addDate.getTime() + 500;
				//如果超过时间那么就重新发送
				if ( date || back ) {
					//删除该图片，并重新生成
					imageService.deleteImage(image);
					File file = new File(saveUrl + image.getFileName());
					if (file.exists()) {
						file.delete();
					}
					getImageQrcode(openId, response);
				}
				
				fis = new FileInputStream(saveUrl + image.getFileName());
				os = response.getOutputStream();  
		        int count = 0;  
		        byte[] buffer = new byte[1024 * 8];  
	            while ((count = fis.read(buffer)) != -1) {  
	                os.write(buffer, 0, count);  
	                os.flush();  
	            }  
			}else {
				getImageQrcode(openId, response);
			}
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ResUtils.execRes();
	}
	/**
	 * 获取二维码图片
	 * @param openId
	 * @param response
	 * @throws Exception 
	 */
	private void getImageQrcode(String openId, HttpServletResponse response) throws Exception {
		String access_token = getAccessToken();
		if (StringUtils.isNotBlank(access_token)) {
			Map<String,Object> map = new LinkedHashMap<String,Object>();
			//保存30天
			map.put("action_name", "QR_STR_SCENE");
			map.put("expire_seconds", 2592000);
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			HashMap<String, String> teMap = new HashMap<String,String>();
			teMap.put("scene_str", openId);
			hashMap.put("scene",teMap);
			map.put("action_info", hashMap);
			//创建永久的二维码
			HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(map);
			String result  = restTemplate.postForObject(ContextConstant.WEIXIN_QRCODE_CREATE + "?access_token="+ access_token,httpEntity,String.class);
			if (result != null) {
				JSONObject resultJson = JSONObject.parseObject(result);
				if (resultJson != null) {
					String qrcodeUrl = resultJson.getString("url");
					if (qrcodeUrl != null ) {
						//根据url生成二维码
						ImagePo backimage = imageService.getBackageImage();
						BufferedImage compositeImage;
						if (backimage == null || StringUtils.isBlank(backimage.getFileName())) {
							compositeImage = QrcodeUtils.createImage(qrcodeUrl, this.codeWidth, this.codeHight);
						}else {
							compositeImage= QrcodeUtils.compositeImage(qrcodeUrl,this.saveUrl + backimage.getFileName(), this.qrcodestartX, this.qrcodestartY, this.codeWidth, this.codeHight);
						}
						
						String imageName = System.nanoTime() + ".jpg";
						ImageIO.write(compositeImage, "JPG", new File(saveUrl + imageName));
						//将保存的本地的数据放入数据库
						ImagePo imagePo = new ImagePo();
						imagePo.setFileName(imageName);
						imagePo.setUserOpenid(openId);
						imagePo.setCreateTime(new Date());
						imagePo.setImageType(1);
						if (backimage != null) {
							imagePo.setBackgroudId(backimage.getImageId());
						}
						
						imageService.saveImage(imagePo);
						ImageIO.write(compositeImage , "JPEG", response.getOutputStream());
					}
					
				}
			}
		}
	}
	/**
	 * 用来获取access_token
	 * @return
	 */
	private String getAccessToken() {
		try {
			HashMap<String, String> hashMap = new HashMap<String,String>();
			hashMap.put("grant_type", "client_credential");
			hashMap.put("appid", this.appid);
			hashMap.put("secret",this.secret );
			String result = restTemplate.getForObject(ContextConstant.WEIXIN_ACCESS_TOKEN + "?grant_type={grant_type}&appid={appid}&secret={secret}", String.class, hashMap);
			
			if (result != null) {
				JSONObject tokenObject = JSON.parseObject(result);
				if (tokenObject != null) {
					return tokenObject.getString("access_token");
				}
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}
	
	/**
	 * 获取报名人信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getRegist")
	public String getRegist(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			
			UserPo userPo = super.getRegistPoByToken(userToken);
			List<RegistVo> list = new ArrayList<>();
			if(null!=userPo){
				RegistVo nameVo = new RegistVo();
				nameVo.setData_type("name");
				nameVo.setName(userPo.getUsername());
				list.add(nameVo);
				RegistVo papersVo = new RegistVo();
				papersVo.setData_type("papers");
				papersVo.setName(userPo.getCardType());
				list.add(papersVo);
				RegistVo papersNumVo = new RegistVo();
				papersNumVo.setData_type("papers_num");
				papersNumVo.setName(userPo.getCardNum());
				list.add(papersNumVo);
				RegistVo areaVo = new RegistVo();
				areaVo.setData_type("area");
				areaVo.setName("1");
				list.add(areaVo);
				RegistVo phoneVo = new RegistVo();
				phoneVo.setData_type("phone");
				phoneVo.setName(userPo.getPhone());
				list.add(phoneVo);
			}
			return ResUtils.okRes(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 获取默认用户的详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUser")
	public String getUser(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			if(StringUtils.isBlank(userToken)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			UserPo userPo = userService.find(super.getUserIdByToken(userToken));
			return ResUtils.okRes(userPo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 修改通知状态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateNotificationStatus")
	public String updateNotificationStatus(HttpServletRequest request){
		try {
			String userToken = request.getParameter("userToken");
			String notificationStatus = request.getParameter("notificationStatus");
			if(StringUtils.isBlank(userToken) || StringUtils.isBlank(notificationStatus)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			UserPo userPo = new UserPo();
			userPo.setUserId(super.getUserIdByToken(userToken));
			userPo.setStandby1(notificationStatus);
			userService.updateOne(userPo);
			return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
	
	/**
	 * 升级代理
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateIsAgency")
	public String updateIsAgency(HttpServletRequest request,@RequestParam(required = false, value = "userIds[]") List<String> userIds){
		try {
			String isAgency = request.getParameter("isAgency");
			if(null == userIds || userIds.size() < 0 || StringUtils.isBlank(isAgency)){
				return ResUtils.errRes("102", "请求参数错误");
			}
			for (String userId : userIds) {
				if(StringUtils.isNumeric(userId)){
					UserPo userPo = userService.find(Integer.parseInt(userId));
					userPo.setIsAgency(isAgency);
					userService.updateOne(userPo);
					String openid = userPo.getOpenid();
				    String userToken = MD5Util.md5Encode(MD5Util.md5Encode(openid));
				    userTokenCache.addUserToken(userToken, userPo);
				}
			}
		    return ResUtils.okRes();
		} catch (Exception e) {
			e.printStackTrace();
			return ResUtils.execRes();
		}
	}
}
