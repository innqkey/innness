package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
* 类说明：图片处理类
* @author 
* @version 创建时间：2018年2月7日 下午6:48:14
* 
*/
public class ImageUtils {

	public static String GetImageStr(FileInputStream fileInputStream) {
	    InputStream in = fileInputStream;  
        byte[] data = null;  
        try{  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }catch (IOException e) {  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
	}

	public static byte[] GenerateImage(String backBIS64) throws Exception {
		   BASE64Decoder decoder = new BASE64Decoder();
		   byte[] b = decoder.decodeBuffer(backBIS64);  
		return b;
	}

}
