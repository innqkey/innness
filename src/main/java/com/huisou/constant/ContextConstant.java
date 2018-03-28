package com.huisou.constant;

import java.util.regex.Pattern;

/**
 * 用来定影全局变量的
 *
 * @author Administrator
 */
public class ContextConstant {


    //上传异常,701图片为空，702图片超出大小，709图片的类型错误，704 服务器异常
    public static final String IMAGE_NULL = "701";
    public static final String SIZE_EXCEEDS = "702";
    public static final String IMAGE_TYPE = "703";
    public static final String UPLOAD_FAILURE = "704";

    //图片状态
    public static final Integer PIC_STATUS_EXIST = 0;
    public static final Integer PIC_STATUS_DELETE = 1;

    //用户或角色已存在
    public static final String EXIST_NAME = "102";
    //必传参数为空
    public static final String PARAM_NULL = "401";
    //token
    public static final String SESSION_TOKEN = "token";
    //userToken
    public static final String SESSION_USERTOKEN = "userToken";
    
    //软删除状态（可通用）
    public static final String EXIST_STATUS = "1";
    public static final String DELETE_STATUS = "2";
    
    /**
     * 错误的金额
     */
    public static final String PAY_FAIL_ERROR_NUM= "错误的金额";
    /**
     * 错误的商户号
     */
    public static final String PAY_FAIL_ERROR_ACCOUNT= "错误的商户号";
    /**
     * 错误的订单号
     */
    public static final String PAY_FAIL_ERROR_ORDERID= "错误的订单号";
    public static final String PAY_FAIL_ERROR_NULLODERID= "空的订单号";
    
    //数据库判断是否（可通用）
    public static final String YES = "Y";
    public static final String NO = "N";
    
   //订单支付状态:1,没有支付; 2,支付成功; 3,支付失败;
    public static final String PAY_STATUS_NO="1";
    public static final String PAY_STATUS_SUCCESS="2";
    public static final String PAY_STATUS_FAIL="3";
   
    //是否开启jsonp跨域，开发时开启，用于前端本地调试接口;1-返回json格式,2-返回jsonp格式，上线改为1
    public static final String IS_JSON = "1";
    public static final String IS_JSONP = "2";
    public static final int REDES_DATABASE1 = 1;
    //微信获取access_token接口
    public static final String WEIXIN_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";
    //获取微信二维码的创建
    public static final String WEIXIN_QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    //使用url的过滤：
	public static final String URL_PATTERN =  "^(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?$";    
    //访问类型
    public static final String VISIT_RECORD_TYPE_YP = "YP";  //音频
    public static final String VISIT_RECORD_TYPE_SP = "SP";  //视频
    public static final String VISIT_RECORD_TYPE_DL = "DL";  //登陆
    public static final String VISIT_RECORD_TYPE_ZL = "ZL";  //资料
    
    //消息的类型小时
	public static final String MES_TEXT = "1";
	//2表示图片的类型
	public static final String MES_IMAGE = "2";
	
	//历史消息是否被删除
	public static final String HISTORY_EXIT= "1";
    public static final String HISTORY_DELETED="2";
    

    //使用url的过滤：
  	public static final Pattern EMOJI = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,
  	         Pattern.UNICODE_CASE|Pattern.CASE_INSENSITIVE);

  	//用户类型:1,普通员工;2,代理人;3,员工
  	public static final String AGENT_USER="1";
  	public static final String AGENT_PROXY="2";
  	public static final String AGENT_EMP="3";
 
  	//默认返现级别:代理人、员工默认返现级别，默认返回1
  	public static final Integer REBATE_LEVEL=1;
  	
	//新用户关注后发送的信息
	public static final String ATTENTION = "恭喜您已经关注会搜商学院网络服务。在此，我们为您精心准备了大量的培训视频、音频和何耀东老师的经典语录，"
			+ "以及海量的“移动互联网实战运营干货”和“行业资料”。应《中华人民共和国网络安全法》要求，同时为了倡导绿色、安全的网络学习环境，敬请您进行身份证实名认证，认证后您就可以随心所欲的享受我们为您提供的服务…… "
			+ "您的身份一经认证，我们将会赠送您1000积分至您的会员账户中。邀请一名好友成功注册您还能再获得100积分。您可以使用您的积分下载和使用平台各类“学习资料”。"
			+ "为了享受更优质的服务，建议您马上进入个人中心认证身份！";
	
	/**
	 * 1是退款申请
	 */
	public static final Integer REFUND_APPLY_CREATE = 1;
	/**
	 * 2是退款申请成功
	 */
	public static final Integer REFUND_APPLY_SUCCESS = 2;
	/**
	 *3是退款成功
	 */
	public static final Integer REFUND_SUCCESS = 3;
	/**
	 * 4是退款申请失败
	 */
	public static final Integer REFUND_APPLY_FAIL = 4;
	/**
	 * 5是退款异常
	 */
	public static final Integer REFUND_FAIL = 5;
	/**
	 * 6是退款关闭
	 */
	public static final Integer REFUND_CLOSE = 6;
	
	
	

}
