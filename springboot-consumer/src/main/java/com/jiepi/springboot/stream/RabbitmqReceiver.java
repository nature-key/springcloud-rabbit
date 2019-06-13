package com.jiepi.springboot.stream;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@EnableBinding(Barista.class)
@Service
public class RabbitmqReceiver {


    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(Message message) throws Exception {

        Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
        Long deliverTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        System.out.println("消费：" + message);
        System.out.println("消费哦--------------------");
        channel.basicAck(deliverTag, false);
    }

}
