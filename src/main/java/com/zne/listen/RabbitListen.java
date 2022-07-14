package com.zne.listen;

import com.zne.entity.Mes;
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
public class RabbitListen {
    /**
     * 定义此方法为队列mes的监听器，一旦监听到新的消息，就会接受并处理
     */
//    @RabbitListener(queues = "mes", messageConverter = "jacksonConverter")
    public String test(Mes mes) {
        log.info("开始消费消息：{}", mes);
        return "消费成功！";
    }

    @RabbitListener(queues = "dl-mes", messageConverter = "jacksonConverter")
    public void receiver(Mes mes) {
        log.info("死信队列开始消费消息：{}", mes);
    }

    @RabbitListener(queues = "mes")
    public void receiver(String data) {
        log.info("一号消息队列监听器:{}", data);
    }

    @RabbitListener(queues = "mes")
    public void receiver2(String data) {
        log.info("二号消息队列监听器:{}", data);
    }
}
