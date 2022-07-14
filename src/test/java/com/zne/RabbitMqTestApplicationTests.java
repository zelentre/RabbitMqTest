package com.zne;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class RabbitMqTestApplicationTests {

    @Resource
    private RabbitTemplate template;

    @Test
    void contextLoads() {
        //使用convertAndSend方法一步到位，参数基本和之前是一样的
        //最后一个消息本体可以是Object类型，真是大大的方便
//        template.convertAndSend("amq.direct", "my-mes", new Mes(1,"233"));
        Object o = template.convertSendAndReceive("amq.direct", "my-mes", "233");
        log.info("收到消费者响应：{}", o);
    }

}
