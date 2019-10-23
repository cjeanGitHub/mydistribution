package com.cjean.acrivemq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsCusmo {
	
	private static final String USER = ActiveMQConnection.DEFAULT_USER;
	private static final String PWD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

	public static void main(String[] args) throws JMSException {
		//  创建 jms下消息队列链接工厂
		ConnectionFactory connectionFactory = null;
		// 创建 链接
		Connection connection = null;
		// 创建会话
		Session session = null;
		// 创建目标地址
		Destination destination = null;
		//创建 消息消费者
		MessageConsumer messageConsumer = null;
		// 实例化 链接工厂  实现Activemq
		connectionFactory = new ActiveMQConnectionFactory(JmsCusmo.USER,JmsCusmo.PWD,JmsCusmo.BROKEURL);
		//实例化 链接
		connection = connectionFactory.createConnection();
		connection.start();
		//实例化对话
		session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//创建目标标识
		destination = session.createQueue("taleMom2");
		//实例化消费之
		messageConsumer = session.createConsumer(destination);

		while(true) {
			TextMessage textMessage = (TextMessage)messageConsumer.receive(10000);
		//	System.out.println(textMessage.toString());
			if(textMessage!=null) {
				System.out.println("收到儿子的会话："+textMessage);
				System.out.println("收到儿子的会话："+textMessage.getText());
			}else {
			//	connection.close();
				break;
			}
			
		}
	}

}
