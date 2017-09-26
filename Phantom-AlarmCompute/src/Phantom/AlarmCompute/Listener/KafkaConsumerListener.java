package Phantom.AlarmCompute.Listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;


public class KafkaConsumerListener implements MessageListener<String,String>{

	@Override
	public void onMessage(ConsumerRecord<String, String> record) {
		// TODO Auto-generated method stub
		String topic = record.topic();
		System.out.println(topic);
		
	}


}
