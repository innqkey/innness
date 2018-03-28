package com.huisou.weixin.config;



import org.springframework.stereotype.Component;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 重写微信支付的接受类
 * 注释中各行每个字段描述对应如下：
 * <li>字段名
 * <li>变量名
 * <li>是否必填
 * <li>类型
 * <li>示例值
 * <li>描述
 * Created by Binary Wang on 2016-10-08.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Component
public class WxPayRefundRequestTemp extends WxPayRefundRequest {

  
  @XStreamAlias("notify_url")
  private String notifyUrl;

	public String getNotifyUrl() {
		return notifyUrl;
	}
	
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
  

  
}
