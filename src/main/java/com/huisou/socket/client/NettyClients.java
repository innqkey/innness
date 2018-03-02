package com.huisou.socket.client;

import java.util.List;


import com.corundumstudio.socketio.SocketIOClient;

/**
 * nettyclient的工具类
 * 用于存放，广播，删除 nettyAgengClient，和UserClient对应的sockIoClient
 * 
 * @author Administrator 
 */
public class NettyClients {
	
	private static NettyClients clients = new NettyClients();
	
	private NettyUserClient userClients = new NettyUserClient();
	/**
	 * 获取nettyClient中的实例对象
	 * @return
	 */
	public static NettyClients getInstance(){
		return clients ;
	}
	
	/**
	 * 设置新的userClient
	 * @param imClients
	 */
	public void setUserClients(NettyUserClient imClients) {
		this.userClients = imClients;
	}
	/**
	 * 更具userid的id，放入新的socketIoClient
	 * @param id
	 * @param userClient
	 */
	public void putUserEventClient(String userId , SocketIOClient userClient){
		userClients.putClient(userId, userClient);
	}
	/**
	 * 更具user的id和sessionid，删除SocketIoClient
	 * @param id
	 * @param sessionid
	 */
	public void removeUserEventClient(String userId){
		userClients.removeClient(userId);
	}
	/**
	 * 根据id，发送消息
	 * @param id
	 * @param event
	 * @param data
	 */
	public void sendUserEventMessage(String userId, String event , Object data){
		SocketIOClient userSocketClients = this.userClients.getClients(userId) ;
		if (userSocketClients != null) {
			userSocketClients.sendEvent(event, data);
		}
	}
	
	

}
