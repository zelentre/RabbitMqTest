package com.zne.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZNE
 * @since 2022/7/13
 */
@Configuration
public class RabbitConfiguration {
    /**
     * 定义交换机Bean，可以很多个
     */
    @Bean("directExchange")
    public Exchange exchange() {
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

    /**
     * 定义消息队列
     */
    @Bean("mesQueue")
    public Queue queue() {
        return QueueBuilder
                //非持久化类型
                .nonDurable("mes")
                .build();
    }

    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange,
                           @Qualifier("mesQueue") Queue queue) {
        //将我们刚刚定义的交换机和队列进行绑定
        return BindingBuilder
                //绑定队列
                .bind(queue)
                //到交换机
                .to(exchange)
                //使用自定义的routingKey
                .with("my-mes")
                .noargs();
    }

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
