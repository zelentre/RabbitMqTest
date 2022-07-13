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
                //指定死信交换机
                .deadLetterExchange("dlx.direct")
                //指定死信RoutingKey
                .deadLetterRoutingKey("dl-mes")
                .ttl(5000)
                .build();
    }

    /**
     * 将我们刚刚定义的交换机和队列进行绑定
     */
    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange,
                           @Qualifier("mesQueue") Queue queue) {
        return BindingBuilder
                //绑定队列
                .bind(queue)
                //到交换机
                .to(exchange)
                //使用自定义的routingKey
                .with("my-mes")
                .noargs();
    }

    /**
     * 创建一个新的死信交换机
     */
    @Bean("directDlExchange")
    public Exchange dlExchange() {
        return ExchangeBuilder.directExchange("dlx.direct").build();
    }

    /**
     * 创建一个新的死信队列
     */
    @Bean("mesDlQueue")
    public Queue dlQueue() {
        return QueueBuilder
                .nonDurable("dl-mes")
                .build();
    }

    /**
     * 死信交换机和死信队列进绑定
     */
    @Bean("dlBinding")
    public Binding dlBinding(@Qualifier("directDlExchange") Exchange exchange,
                             @Qualifier("mesDlQueue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("dl-mes")
                .noargs();
    }

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
