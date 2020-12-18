package cn.zhou.codedemo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = "test_mq";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("test");
        factory.setPassword("test");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        //    void basicPublish(String var1, String var2, BasicProperties var3, byte[] var4) throws IOException;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        for (int i = 0; i < 100; i++) {
            message = "QUEUE_NAME:" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
