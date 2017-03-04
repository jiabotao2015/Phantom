/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.MessageQueue
* @Files: MessageConnect.java
* @Description: 消息通道，包含一个内存队列
* @author jiabotao 
* @date 2017年3月4日 下午11:51:05
* @version V1.0   
*/ 
package Phantom.Common.MessageQueue;

import java.util.concurrent.LinkedTransferQueue;

public class MessageConnector {
	
	LinkedTransferQueue<MessageBean> linkq = new LinkedTransferQueue<MessageBean>();
	
	private String connectorName = "Defalut";
	
	/**
	 * @Description: 消息通道构造函数
	 * @param connectName
	 */
	public MessageConnector(String connectorName) {
		this.connectorName = connectorName;
	}
	
	/**
	 * @Description: 获取消息通道名
	 * @return
	 */
	public String getConnectorName() {
		return this.connectorName;
	}
	
	public boolean offerMessage(MessageBean message){
		return linkq.offer(message);
	}
	
	public MessageBean takeMassage() throws InterruptedException{
		return linkq.take();
	}
	
	public int getSize(){
		return linkq.size();
	}

}
