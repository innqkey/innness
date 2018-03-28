package com.huisou.constant;

import org.apache.commons.lang.StringUtils;


public class DictConConstant {
	
	//帐号类型
	public enum CardType{
		cardType1("1","身份证"),cardType2("2","护照");
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        CardType(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
	}
	
	//用于所有删除状态显示
	public enum Status{
		//客户状态:1,正常;2,禁用;3,待续费
		status1("1","正常"), status2("2","删除");
        
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Status(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	//用于所有支付状态显示
	public enum PayStatus{
		//支付状态：1-未支付；2-已支付；3-支付失败
		PayStatus1("1","未支付"), PayStatus2("2","已支付"), PayStatus3("3","支付失败");
        
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        PayStatus(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	//用于所有Y和N状态显示
	public enum IsYNStatus{
		//客户状态:Y-是;N-否;
		isYNStatus1("Y","是"), isYNStatus2("N","否");
        
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        IsYNStatus(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	public enum Evaluationtype{
		//评价类型：1好;2一般;3差
		Evaluationtype1("1","好"), Evaluationtype2("2","一般"), Evaluationtype3("3","差");
        
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Evaluationtype(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	public enum Evaluatetype{
		Evaluatetype1("1","差"),Evaluatetype2("2","一般"),Evaluatetype3("3","中等"),Evaluatetype4("4","良好"),Evaluatetype5("5","优");
		
		private String i;
        private String val;
        Evaluatetype(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
	}
	
	public enum YesOrNoType{
		YesOrNoType1("1","是"),YesOrNoType2("0","否");
		private String i;
        private String val;
        YesOrNoType(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
	}
	
	
	public enum Auditstatus{
		//审核状态：1审核通过;2审核不通过
		Auditstatus0("0","未审核"),Auditstatus1("1","审核通过"), Auditstatus2("2","审核不通过");
        
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        Auditstatus(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	public enum ReplyStatus{
		ReplyStatus0("0","不需要回复"),ReplyStatus1("1","需要回复"),ReplyStatus2("2","已回复");
		
		private String i;
        private String val;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        ReplyStatus(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
	}
	//用户认证
	public enum AuthStatus{
		//用户认证状态:1,已认证;2,未认证;
		AuthStatus1("1","已认证"), AuthStatus2("2","未认证");
		private String i;
        private String val;
        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        AuthStatus(String i,String val) {
        	this.i = i;
            this.val = val;
        }
        
        public String getVal() {
            return val;
        }
        public String getI() {
            return i;
        }
    }
	
	/**
	 * 银行卡类型
	 * 功能描述：
	 * @Package: com.huisou.constant 
	 * @author: Administrator   
	 * @date: 2018年3月23日 下午7:26:38
	 */
	public enum BankType{
		BankType1("YH","银行卡"),BankType2("ZFB","支付宝"),BankType3("WX","微信");
		private String i;
        private String val;
        BankType(String i,String val) {
			this.i = i;
			this.val=val;
		}
		public String getVal() {
	            return val;
        }
        public String getI() {
            return i;
	    }
	}
	
	/**
	 * 提现审核状态
	 * 功能描述：
	 * @Package: com.huisou.constant 
	 * @author: Administrator   
	 * @date: 2018年3月23日 下午7:26:30
	 */
	public enum  AuditingStatus{
		AuditingStatus1("1","未审核"),AuditingStatus2("2","审核成功"),AuditingStatus3("3","审核失败");
		private String i;
        private String val;
        AuditingStatus(String i,String val) {
			this.i = i;
			this.val=val;
		}
		public String getVal() {
	            return val;
        }
        public String getI() {
            return i;
	    }
	}
	
	public enum MesType{
		MesType1("1","text"), MesType2("2","image");
		private String i;
        private String val;
		MesType(String i,String val) {
			this.i = i;
			this.val=val;
		}
		public String getVal() {
	            return val;
        }
        public String getI() {
            return i;
	    }
	}

	public static String getDicName(String type,String typeVal){
		if(StringUtils.isBlank(type)||null==typeVal){
			return "";
		}
		type = type.toUpperCase();
		
		if(DictConConstant.Status.class.getName().toUpperCase().contains(type)){
			for(Status itmeType : Status.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.PayStatus.class.getName().toUpperCase().contains(type)){
			for(PayStatus itmeType : PayStatus.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.PayStatus.class.getName().toUpperCase().contains(type)){
			for(PayStatus itmeType : PayStatus.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
	        }
		}
		
		if(DictConConstant.Evaluatetype.class.getName().toUpperCase().contains(type)){
			for(Evaluatetype itmeType : Evaluatetype.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
			}
		}

		if(DictConConstant.AuthStatus.class.getName().toUpperCase().contains(type)){
			for(AuthStatus itmeType : AuthStatus.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
			}
		}
		
		
		if(DictConConstant.MesType.class.getName().toUpperCase().contains(type)){
			for(MesType itmeType : MesType.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
			}
		}
		if(DictConConstant.BankType.class.getName().toUpperCase().contains(type)){
			for(BankType itmeType : BankType.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
			}
		}
		if(DictConConstant.AuditingStatus.class.getName().toUpperCase().contains(type)){
			for(AuditingStatus itmeType : AuditingStatus.values()){
				if(itmeType.i.equals(typeVal)){
					return itmeType.val;
				}
			}
		}
		
		return null;
	}
	
}
