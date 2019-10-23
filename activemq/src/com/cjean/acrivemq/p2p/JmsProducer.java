package com.cjean.acrivemq.p2p;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JmsProducer {
	
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
		//创建 消息生产者
		MessageProducer messageProducer = null;
		
		// 实例化 链接工厂  实现Activemq
		
		connectionFactory = new ActiveMQConnectionFactory(
				JmsProducer.USER,JmsProducer.PWD,JmsProducer.BROKEURL
				);
		
		try {
			//实例化链接
			connection = connectionFactory.createConnection();
			connection.start();
			//实例化会话
			session  = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			//实例化目标地址(创建  消息队列)
			destination = session.createQueue("taleMom2");
			// 实例化 消息生产者
			messageProducer = session.createProducer(destination);
			
			for(int i=0;i<5;i++) {
				TextMessage textMessage = session.createTextMessage("说给妈妈的话："+i);
				System.out.println("打印会话内容，text"+textMessage.getText());
				messageProducer.send(textMessage);
			}
			session.commit();
			
		} catch (JMSException e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}

	}

}
