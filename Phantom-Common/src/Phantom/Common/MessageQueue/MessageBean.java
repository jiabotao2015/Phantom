/**   
* @Project: Phantom-Common
* @Title: 
* @Package Phantom.Common.MessageQueue
* @Files: MessageBean.java
* @Description: 消息对象，无论是啥消息，都统一构造成此对象，方便MessageManager和MessageConnect管理
* @author jiabotao 
* @date 2017年3月4日 下午11:59:11
* @version V1.0   
*/ 
package Phantom.Common.MessageQueue;

import java.io.Serializable;

public class MessageBean implements Serializable{
	
	private static final long serialVersionUID = 7130423555045489063L;

	private String messageType;
	
	private String messageKey;
	
	private String messageValue;

	/**
	 * @return the messageType
	 */
	public String getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the messageKey
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * @param messageKey the messageKey to set
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the messageValue
	 */
	public String getMessageValue() {
		return messageValue;
	}

	/**
	 * @param messageValue the messageValue to set
	 */
	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}
	
	
	
	

}
