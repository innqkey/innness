package com.huisou.weixin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.ResUtils;
import com.github.pagehelper.PageInfo;
import com.huisou.constant.ContextConstant;
import com.huisou.po.TemplateContentPo;
import com.huisou.po.TemplateSendHistoryPo;
import com.huisou.service.TemplateContentService;
import com.huisou.service.TemplateSendHistoryService;
import com.huisou.service.UserService;
import com.huisou.vo.PageTemp;
import com.huisou.vo.TemplateContentVo;
import com.huisou.vo.TemplateHistoryVo;
import com.huisou.weixin.handler.TemplateHandler;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

/**
* 类说明：用于微信模板的添加，删除，修改，发送
* @author 
* @version 创建时间：2018年3月8日 下午1:34:14
* 
*/
@RequestMapping("/template")
@RestController
@Component
public class WeChatTemplateController {
	@Autowired
	private WxMpService wxService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TemplateContentService templateContentService;
	
	@Autowired
	private TemplateSendHistoryService templateHistoryService;
	
	@Value("${templateId.send}")
	private String sendTemplateId;
	

	
	/**
	 * 模板历史删除
	 * @param pageTemp
	 * @return
	 */
	@RequestMapping("/templateHistory")
	public String templateHistoryList(PageTemp pageTemp) {
		PageInfo<TemplateHistoryVo> list = templateHistoryService.findAll(pageTemp);
		return ResUtils.okRes(list);
	}
	
	/**
	 * 删除模板历史
	 * @param historyId
	 * @return
	 */
	@RequestMapping("/deleteHistory")
	public String deleteHistory(Integer historyId) {
		templateHistoryService.deleteHistory(historyId);
		return ResUtils.okRes();
		
	}
	
	@RequestMapping("/batchDeleteHistory")
	public String batchDeleteHistory(@RequestParam("historyIds[]")List<Integer> historyIds) {
		if (historyIds != null && historyIds.size() > 0) {
			templateHistoryService.batchDeleteHistory(historyIds);
		}
		
		return ResUtils.okRes();
	}
	
	
	
	
	

	/**
	 * 添加模板
	 * @param po
	 * @return
	 */
	@RequestMapping(value = "/addOrEdit",method = RequestMethod.POST)
	public String addTemplateContent( TemplateContentPo po) {
		if (po == null ||  po.getKeyword1() == null ) {
			return ResUtils.execRes("参数不能为空");
		}
		po.setCreateTime(new Date());
		templateContentService.addOrEdit(po);
		return ResUtils.okRes();
	}
	
	
	
	
	/**
	 * 根据id获取模板的内容
	 * @param id
	 * @return
	 */
	@RequestMapping("/getTemplate")
	public String getById(Integer id) {
		if (id == null) {
			return ResUtils.execRes("参数不能为空");
		}
		TemplateContentPo po = templateContentService.getById(id);
		return ResUtils.okRes(po);
	}
	

	/**
	 * 删除模板
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String deleteTemplateContent(Integer templateId) {
		if (templateId == null) {
			return ResUtils.execRes("参数不能为空");
		}
		templateContentService.delete(templateId);
		return ResUtils.okRes();
	}
	
	
	@RequestMapping("/deleteBatch")
	public String deleteBatch(@RequestParam("contentIds[]")List<Integer> contentIds) {
		if (contentIds == null || contentIds.size() < 1) {
			return ResUtils.execRes("参数不能为空");
		}
		templateContentService.deleteBatch(contentIds);
		return ResUtils.okRes();
	}
	
	/**
	 * 模板的list展示
	 * @param po
	 * @return
	 */
	@RequestMapping("/list")
	public String listTemplateContent(PageTemp pageTemp) {
		PageInfo<TemplateContentVo> result = templateContentService.findAll(pageTemp);
		return ResUtils.okRes(result);
	}

	/**
	 * 批量发送消息
	 * 
	 * @throws WxErrorException
	 */
	@RequestMapping("/batchSend")
	public String sendBatchTemplateMsg(Integer templateContentId) {
		try {
			if (templateContentId == null) {
				return ResUtils.execRes("参数不能为空");
			}
			
			TemplateContentPo templatetPo = templateContentService.getById(templateContentId);
			//不为空的时候就发送对应的数据
			if (templatetPo != null) {
				ArrayList<WxMpTemplateData> templateDataList = new ArrayList<WxMpTemplateData>();
				templateDataList.add(new WxMpTemplateData("first",templatetPo.getFirst() ));
				templateDataList.add(new WxMpTemplateData("keyword1",templatetPo.getKeyword1() ));
				templateDataList.add(new WxMpTemplateData("keyword2",templatetPo.getKeyword2()));
				templateDataList.add(new WxMpTemplateData("keyword3",templatetPo.getKeyword3() ));
				templateDataList.add(new WxMpTemplateData("keyword4",templatetPo.getKeyword4()));
				templateDataList.add(new WxMpTemplateData("remark",templatetPo.getRemark(),"#4791ff"));
				
				ExecutorService pool = Executors.newFixedThreadPool(10);
				List<String> openIds = userService.findAllOpenid();
				TemplateSendHistoryPo templateSendHistory = new TemplateSendHistoryPo();
				if (openIds != null && openIds.size() > 0) {
					for (String openid : openIds) {
						if (openid != null) {
							pool.execute(new MsgRunner(sendTemplateId, openid,templateDataList,templatetPo.getUrl()));
						}
					}
					templateSendHistory.setSendAmount(openIds.size());
				}else {
					templateSendHistory.setSendAmount(0);
				}
				//成功之后保存历史记录
				templateSendHistory.setCreateTime(new Date());
				templateSendHistory.setTemplateContentId(templatetPo.getMsgTemplateId());
				templateSendHistory.setExistStatus(1);
				templateSendHistory.setNoticeType("公众号");
				templateSendHistory.setTemplateId(sendTemplateId);
				templateSendHistory.setTemplateType("订阅课程开课提醒");
				templateHistoryService.insertOne(templateSendHistory);
				return ResUtils.okRes("发送成功");
				
			}else {
				return ResUtils.execRes("模板消息为空");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResUtils.execRes(e);
		}

	}

	
	
//	/**
//	 * 获取所有的模板
//	 * @throws Exception
//	 */
//	public void getAllPrivateTemplate() throws Exception {
//		List<WxMpTemplate> result = this.wxService.getTemplateMsgService().getAllPrivateTemplate();
//		for (WxMpTemplate wxMpTemplate : result) {
//			
//		}
//	}
//	
	
	

	 class MsgRunner implements Runnable {
		private String templateId;
		private String openId;
		List<WxMpTemplateData> wxMpTemplateDatas;
		String url;

		public MsgRunner(String templateId, String openId,List<WxMpTemplateData> wxMpTemplateDatas,String url) {
			this.templateId = templateId;
			this.openId = openId;
			this.url = url;
			this.wxMpTemplateDatas = wxMpTemplateDatas;
		}

		public void run() {
			try {
				TemplateHandler.sendTemplateMsg(wxService,templateId, openId,wxMpTemplateDatas,url);
			} catch (WxErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
