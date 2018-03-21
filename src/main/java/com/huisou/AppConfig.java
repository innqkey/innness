package com.huisou;



import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

 
/**
 * Created by miaorf on 2016/6/19.
 */
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@MapperScan(basePackages = "com.huisou.mapper")
@EnableJpaRepositories("com.huisou.repository")
@EnableTransactionManagement//开启事务
@SpringBootApplication(scanBasePackages = {"com.example", "com.baidu", "com.huisou"})
//public class AppConfig{
public class AppConfig extends SpringBootServletInitializer{
	private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class);
        logger.info("SpringBoot Start Success");
    }
    
	@Autowired  
    private RestTemplateBuilder builder;  
  
 	// 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
    @Bean  
    public RestTemplate restTemplate() {
    	
    	builder.setConnectTimeout(5000);
    	builder.setReadTimeout(15000);
    	RestTemplate build = builder.build();
    	
        return build;
    }
    
    //分支测试
/*   @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
      FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
     FastJsonConfig fastJsonConfig = new FastJsonConfig();
      fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
      fastConverter.setFastJsonConfig(fastJsonConfig);
     HttpMessageConverter<?> converter = fastConverter;
      return new HttpMessageConverters(converter);
    }
    */
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
       // 注意这里要指向原先用main方法执行的AppConfig启动类
       return builder.sources(AppConfig.class);
  }
}
