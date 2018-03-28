package com.huisou.jms.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
* @author 作者 :yuhao 
* @version 创建时间：2018年3月20日 下午1:43:36 
* 类说明 rabbitMqpei配置类（键）
*/
@Configuration
public class QueueConf {

	@Bean(name="payCourseSender1")
	public Queue payCourseSender(){
		return new Queue("topic.payCourseSender1");
	}
	@Bean(name="payCourseSender2")
    public Queue queueMessage() {
        return new Queue("topic.payCourseSender2");
    }
    @Bean(name="payCourseSender3")
    public Queue queueMessages() {
        return new Queue("topic.payCourseSender3");
    }
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }
    @Bean
    Binding bindingExchangePayCourseSender1(@Qualifier("payCourseSender1") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.payCourseSender");//*表示一个词,#表示零个或多个词
    }
    @Bean
    Binding bindingExchangePayCourseSender2(@Qualifier("payCourseSender2") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.payCourseSender");//*表示一个词,#表示零个或多个词
    }
    @Bean
    Binding bindingExchangePayCourseSender3(@Qualifier("payCourseSender3") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.payCourseSender");//*表示一个词,#表示零个或多个词
    }
}
