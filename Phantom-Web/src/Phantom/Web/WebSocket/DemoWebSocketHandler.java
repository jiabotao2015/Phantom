package Phantom.Web.WebSocket;

import java.io.IOException;
//import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import Phantom.Web.Model.User;

public class DemoWebSocketHandler implements WebSocketHandler{
	
	private static Logger logger = Logger.getLogger(DemoWebSocketHandler.class);
	
	private static ConcurrentHashMap<User,WebSocketSession> user_session_map = new ConcurrentHashMap<User,WebSocketSession>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> user_map = session.getAttributes();
		User user = (User) user_map.get("user");
		user_session_map.put(user, session);
		System.out.println("connect to the websocket success......");
		session.sendMessage(new TextMessage("Server:connected OK!"));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 收到消息后的处理
		TextMessage returnMessage = new TextMessage(message.getPayload()  
                + " received at server");  
        System.out.println(session.getHandshakeHeaders().getFirst("Cookie"));  
        session.sendMessage(returnMessage);  

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//  Auto-generated method stub
		System.out.println("websocket connection closed......");  
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		//  Auto-generated method stub
		 System.out.println("websocket connection closed......");  
	}

	@Override
	public boolean supportsPartialMessages() {
		//  Auto-generated method stub
		return false;
	}
	
	public static void sendMessage(User user,String message){
		//ConcurrentHashMap.get(user);
		WebSocketSession session = user_session_map.get(user);
		if(session.isOpen()){
			TextMessage returnMessage = new TextMessage(message);  
			try {
				session.sendMessage(returnMessage);
			} catch (IOException e) {
				logger.error("发送websocket消息失败："+e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
