package com.zne.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZNE
 * @since 2022/7/13
 */
@Configuration
public class RabbitFanoutConfiguration {
    /**
     * 注意这里是fanoutExchange
     */
    @Bean("fanoutExchange")
    public Exchange exchange() {
        return ExchangeBuilder.fanoutExchange("amq.fanout").build();
    }

    @Bean("fanoutQueue1")
    public Queue queue() {
        return QueueBuilder.nonDurable("fanout1").build();
    }

    @Bean("fanoutBinding")
    public Binding binding(@Qualifier("fanoutExchange") Exchange exchange,
                           @Qualifier("fanoutQueue1") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("fanout1")
                .noargs();
    }

    @Bean("fanoutQueue2")
    public Queue queue2() {
        return QueueBuilder.nonDurable("fanout2").build();
    }

    @Bean("fanoutBinding2")
    public Binding binding2(@Qualifier("fanoutExchange") Exchange exchange,
                            @Qualifier("fanoutQueue2") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("fanout2")
                .noargs();
    }
}
