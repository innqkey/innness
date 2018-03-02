package com.huisou.socket.client;


import com.corundumstudio.socketio.SocketIOClient;

public interface NettyClient {
	
	/**
	 * 根据id获取对应的SocketIOClient
	 * @param key
	 * @return
	 */
	public SocketIOClient getClients(String userId) ;
	
	/**
	 * 更具对应的id，放入对应的SocketIOClient
	 * @param key
	 * @param client
	 */
	public void putClient(String userId, SocketIOClient client) ;
	
	/**
	 * 根据sessionId删除对应的SocketIOClient
	 * @param key
	 * @param Sessionid 
	 */
	public void removeClient(String Sessionid) ;
}
