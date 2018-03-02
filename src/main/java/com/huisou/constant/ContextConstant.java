package com.huisou.constant;

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
    
}
