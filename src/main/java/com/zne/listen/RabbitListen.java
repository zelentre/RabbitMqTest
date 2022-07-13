package com.zne.listen;

import com.zne.entity.Mes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZNE
 * @since 2022/7/13
 */
@Slf4j
@Component
public class RabbitListen {
    /**
     * 定义此方法为队列mes的监听器，一旦监听到新的消息，就会接受并处理
     */
    @RabbitListener(queues = "mes", messageConverter = "jacksonConverter")
    public String test(Mes message) {
        log.info("开始消费消息：{}", message);
//        return "{\"body\":\"success\"}";
        return "消费成功！";
    }
}
