package com.common;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
* 类说明： 二维码生成工具类
* @author 
* @version 创建时间：2018年2月8日 上午11:19:53
* 
*/
public class QrcodeUtils {
	/** 
     * 二维码图片的生成 
     * @param content           链接 
     * @param qrcode_width      二维码宽 
     * @param qrcode_height     二维码高 
     * @return 
     * @throws Exception 
     */  
    public  static BufferedImage createImage(String content, int qrcode_width, int qrcode_height) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, qrcode_width, qrcode_height, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000  
                        : 0xFFFFFFFF);  
            }  
        }  
        return image;  
    }  
	
    /** 
     * 合成图片 
     * @param url       二维码链接 
     * @param path      背景图片地址 
     * @param startX    二维码在背景图片的X轴位置 
     * @param startY    二维码在背景图片的Y轴位置 
     * @param codeWidth 二维码宽度 
     * @param codeHeight 二维码高度 
     * @return          合成的图片 
     */  
    public static BufferedImage compositeImage(String url, String path, int startX, int startY, int codeWidth, int codeHeight) {  
    	 FileInputStream fileInputStream=null;
    	 InputStream in = null;
    	try {  
            BufferedImage headImage = createImage(url, codeWidth, codeHeight);  
              
            String backBIS64 = "";   
            Image backImage = null;  
              
            fileInputStream = new FileInputStream(path);  
            backBIS64 = ImageUtils.GetImageStr(fileInputStream);  
                // 读取背景图片  
            in = new ByteArrayInputStream(ImageUtils.GenerateImage(backBIS64));  
            backImage = ImageIO.read(in);
            
            int alphaType = BufferedImage.TYPE_INT_RGB;  
//            if (ImageUtils.hasAlpha(backImage)) {  
//                alphaType = BufferedImage.TYPE_INT_ARGB;  
//            }  
            BufferedImage back = new BufferedImage(backImage.getWidth(null), backImage.getHeight(null), alphaType);  
            // 画图  
            Graphics2D g = back.createGraphics();  
            g.drawImage(backImage, 0, 0, null);  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));  
            g.drawImage(headImage, startX, backImage.getHeight(null) - startY, headImage.getWidth(null), headImage.getHeight(null), null);  
            g.dispose();  
            return back;  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    	return null;  
    }
    
}
