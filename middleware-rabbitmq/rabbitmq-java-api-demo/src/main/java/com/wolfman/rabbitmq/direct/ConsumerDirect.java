package com.wolfman.rabbitmq.direct;

import com.rabbitmq.client.*;
import com.wolfman.rabbitmq.ConnectionUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerDirect {

  private final static String QUEUE_NAME = "comuser_first_queue";

  public static void main(String[] args) throws IOException, TimeoutException {
    //建立连接
    Connection conn = ConnectionUtils.getConnection();

    //创建消息通道
    Channel channel = conn.createChannel();

    // 声明交换机
    // String exchange, String type, boolean durable, boolean autoDelete, Map<String, Object> arguments
    channel.exchangeDeclare("simple_exchange","direct",false, false, null);

    // 声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    // 绑定队列和交换机
    channel.queueBind(QUEUE_NAME,"simple_exchange","simple.first");

    // String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments
    System.out.println(" Waiting for message....");

    // 创建消费者
    DefaultConsumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body, "UTF-8");
        System.out.println("Received message : '" + msg + "'");
      }
    };
    // 开始获取消息
    // String queue, boolean autoAck, Consumer callback
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }


}
