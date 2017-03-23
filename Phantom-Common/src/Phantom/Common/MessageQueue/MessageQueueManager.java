/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.MessageQueue
* @Files: MessageQueueManager.java
* @Description: 
* @author jiabotao 
* @date 2017年3月5日 上午12:03:39
* @version V1.0   
*/ 
package Phantom.Common.MessageQueue;

import java.util.HashMap;

public class MessageQueueManager {
	
	HashMap<String,MessageConnector> messageConnectors_map = new HashMap<String,MessageConnector>();
	
	public static MessageQueueManager Message_Queue_Manager=null;
	
	/**
	 * 向map里添加缓存名和mc对象
	 * @param connectName
	 */
	public MessageQueueManager getInstance() {
		if(Message_Queue_Manager==null){
			Message_Queue_Manager = new MessageQueueManager();
		}
		return Message_Queue_Manager;
	}
	
	public void initMessageConnects(String[] connectorName) {
		for (int i = 0; i < connectorName.length; i++)
			messageConnectors_map.put(connectorName[i], new MessageConnector(connectorName[i]));
	}
	
	/**
	 * @Description:通过消息通道名获取消息通道
	 * @param connectorName
	 * @return MessageConnector
	 */
	public MessageConnector getMessageConnector(String connectorName) {
		return  messageConnectors_map.get(connectorName);
	}

}
