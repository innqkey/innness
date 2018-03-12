package com.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class FtpFileUtil {

	//ftp服务器ip地址
    private static final String FTP_ADDRESS = FileUtil.getApplicationPro("ftp.address");
    //端口号
    private static final int FTP_PORT = Integer.valueOf(null!=FileUtil.getApplicationPro("ftp.port")?FileUtil.getApplicationPro("ftp.port"):"21");
    //用户名
    private static final String FTP_USERNAME = FileUtil.getApplicationPro("ftp.username");;
    //密码
    private static final String FTP_PASSWORD = FileUtil.getApplicationPro("ftp.password");;

    //路径=/video
    public static boolean uploadFile(String originFileName,InputStream input,String ftpBasePath){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(ftpBasePath );
            ftp.changeWorkingDirectory(ftpBasePath );
            ftp.storeFile(originFileName,input);
            input.close();
            ftp.logout();
            System.out.println("-------------");
            success = true;
        } catch (IOException e) {
        	success = false;
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
    
//    public static void main(String[] args){
//    	InputStream inputStream;
//		try {
//			inputStream = new FileInputStream("D:/data/20140420.mp4");
//			uploadFile("20140420.mp4",inputStream);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
}
