package com.zne.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Rabbit消费
 *
 * @author ZNE
 * @since 2022/7/13
 */
@Slf4j
@Component
public class RabbitFanoutListen {

    @RabbitListener(queues = "fanout1")
    public void receiver(String data) {
        log.info("一号消息队列监听器:{}", data);
    }

    @RabbitListener(queues = "fanout2")
    public void receiver2(String data) {
        log.info("二号消息队列监听器:{}", data);
    }
}
