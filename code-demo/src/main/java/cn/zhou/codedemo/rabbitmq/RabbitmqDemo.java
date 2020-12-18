package cn.zhou.codedemo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class RabbitmqDemo {
    public static void main(String[] args) {


    }

    public void mq() throws IOException, TimeoutException {
        String queneName = "testQuene";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        // 创建与RabbitMQ服务器的TCP连接
        Connection connection = null;
        // 创建一个频道
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("test_vhosts");

            // 声明默认的队列
            channel.queueDeclare(queneName, true, false, true, null);
            while (true) {
                channel.basicPublish("", queneName, null, UUID.randomUUID().toString().getBytes());


            }
        } catch (
                Exception ex) {
            ex.printStackTrace();
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

}
