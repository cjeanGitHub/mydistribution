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
		//  ���� jms����Ϣ�������ӹ���
		ConnectionFactory connectionFactory = null;
		// ���� ����
		Connection connection = null;
		// �����Ự
		Session session = null;
		// ����Ŀ���ַ
		Destination destination = null;
		//���� ��Ϣ������
		MessageProducer messageProducer = null;
		
		// ʵ���� ���ӹ���  ʵ��Activemq
		
		connectionFactory = new ActiveMQConnectionFactory(
				JmsProducer.USER,JmsProducer.PWD,JmsProducer.BROKEURL
				);
		
		try {
			//ʵ��������
			connection = connectionFactory.createConnection();
			connection.start();
			//ʵ�����Ự
			session  = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
			//ʵ����Ŀ���ַ(����  ��Ϣ����)
			destination = session.createQueue("taleMom2");
			// ʵ���� ��Ϣ������
			messageProducer = session.createProducer(destination);
			
			for(int i=0;i<5;i++) {
				TextMessage textMessage = session.createTextMessage("˵������Ļ���"+i);
				System.out.println("��ӡ�Ự���ݣ�text"+textMessage.getText());
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
