package com.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**文件上传的工具类
 * Created by loneless on 2017/7/15.
 */
public class UploadUtils {
	private  static  final Logger logger = LoggerFactory.getLogger(UploadUtils.class);
    /**
     * 上传单个图片，参数一是springmvc接收的file
     * ,string 是文件保存地址
     * @param file
     * @param pathname
     * @param userId 
     * @param filetype 上传的类型
     * @return
     */
    public static String uploadimage(MultipartFile file, String pathname){
        if (!file.isEmpty() && StringUtils.isNotBlank(pathname)) {
            //判断文件的类型和文件的大小
            BufferedOutputStream out = null;
            try {
            	pathname=pathname+"/";
            	if(!new File(pathname).exists()){
            		new File(pathname).mkdir();
            	}
                int k = file.getContentType().indexOf("/") + 1;
                String suffix = file.getContentType().substring(k);
                boolean imageType=false;
                String fileName = System.nanoTime()+"."+suffix;
                String url=pathname+fileName;
                logger.info("上传upload地址==="+url);
                out = new BufferedOutputStream(new FileOutputStream(new File(url)));
                out.write(file.getBytes());
                out.flush();
              
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                try {
                	if(out!=null)
                		out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        
        } else {
        }
        return null;
    }
    /**
     * 修改返回值，只是需要图片的名称即可
     * @param file
     * @param pathname
     * @return
     */
    public static String uploadimageReturnFileName(MultipartFile file, String pathname){
    	if (!file.isEmpty() && StringUtils.isNotBlank(pathname)) {
    		//判断文件的类型和文件的大小
    		BufferedOutputStream out = null;
    		try {
    			if(!new File(pathname).exists()){
    				new File(pathname).mkdirs();
    			}
    			int k = file.getContentType().indexOf("/") + 1;
    			String suffix = file.getContentType().substring(k);
    			String image = System.nanoTime()+"."+suffix;
    			String url=pathname+image;
    			logger.info("上传upload地址==="+url);
    			out = new BufferedOutputStream(new FileOutputStream(new File(url)));
    			out.write(file.getBytes());
    			out.flush();
    			
    			return image;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    			try {
    				if(out!=null)
    					out.close();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}
    		
    	} else {
    	}
    	return null;
    }

}
