package com.huisou.socket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.huisou.socket.handler.UserEventHandler;


/**
 * 目前暂时不用
 * 用于项目启动的时候，启动socket服务器
 * 当服务器
 * @author Administrator
 *
 */
//@Component
public class ServerRunner implements CommandLineRunner {
	
	private Logger logger = LoggerFactory.getLogger(ServerRunner.class);
	private final SocketIONamespace imSocketNameSpace;
	private SocketIOServer server;
	@Autowired
	private UserEventHandler userEventHandler;
	

	/**
	 * 用于区分命名的空间就是通过 
	 */
	@Autowired
	public ServerRunner(SocketIOServer server) {
		this.server = server;
		imSocketNameSpace = server.addNamespace("/user");
	}
	
	/**
	 * 为什么不直接通过注入而是将对应的对象交给spring去管理？
	 *  可以便于以后使用对应的名称空间
	 * @return
	 */
	@Bean("imNamespace")   
    public SocketIONamespace getIMSocketIONameSpace(){
    	imSocketNameSpace.addListeners(userEventHandler);
    	return imSocketNameSpace;
    }

	/**
	 * 启动socket服务器
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("socket server  satarting");
		//server.start();
	}

}
