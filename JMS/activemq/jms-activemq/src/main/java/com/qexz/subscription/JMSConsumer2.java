package com.qexz.subscription;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/** 消息消费者-消息订阅者2
 * 先订阅才能接收
 * @author zzqnxx@foxmail.com
 * @date 2018/5/22 16:45
 */
public class JMSConsumer2 {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; //  默认的连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;   //  默认的连接地址

    public static void main(String[] args) {
        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null;  // 连接
        Session session;    //  会话接受或者发送消息的线程
        Destination destination;    // 消息目的地
        MessageConsumer messageConsumer;    //  消息的消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JMSConsumer2.USERNAME,
                JMSConsumer2.PASSWORD, JMSConsumer2.BROKEURL);
        try {
            connection = connectionFactory.createConnection();  //  通过连接工厂获取连接
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); //  创建session
            // destination = session.createQueue("FirstQueue1"); //  创建消息队列
            destination = session.createTopic("FirstTopic1"); //  创建消息队列
            messageConsumer = session.createConsumer(destination);  //  创建消息消费者
            messageConsumer.setMessageListener(new Listener2()); //  注册消息监听
            // 让消息消费者一直监听
            while (true) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
