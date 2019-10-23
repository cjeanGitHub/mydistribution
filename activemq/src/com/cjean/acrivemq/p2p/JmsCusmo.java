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
		//  ���� jms����Ϣ�������ӹ���
		ConnectionFactory connectionFactory = null;
		// ���� ����
		Connection connection = null;
		// �����Ự
		Session session = null;
		// ����Ŀ���ַ
		Destination destination = null;
		//���� ��Ϣ������
		MessageConsumer messageConsumer = null;
		// ʵ���� ���ӹ���  ʵ��Activemq
		connectionFactory = new ActiveMQConnectionFactory(JmsCusmo.USER,JmsCusmo.PWD,JmsCusmo.BROKEURL);
		//ʵ���� ����
		connection = connectionFactory.createConnection();
		connection.start();
		//ʵ�����Ի�
		session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
		//����Ŀ���ʶ
		destination = session.createQueue("taleMom2");
		//ʵ��������֮
		messageConsumer = session.createConsumer(destination);

		while(true) {
			TextMessage textMessage = (TextMessage)messageConsumer.receive(10000);
		//	System.out.println(textMessage.toString());
			if(textMessage!=null) {
				System.out.println("�յ����ӵĻỰ��"+textMessage);
				System.out.println("�յ����ӵĻỰ��"+textMessage.getText());
			}else {
			//	connection.close();
				break;
			}
			
		}
	}

}
