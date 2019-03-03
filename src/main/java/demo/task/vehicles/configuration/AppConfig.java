package demo.task.vehicles.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource("classpath:application.yml")
@EnableMongoRepositories(basePackages = "demo.task.vehicles.repositories")
public class AppConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory());
//        return template;
//    }
//
//    @Bean
//    public RedisConnectionFactory connectionFactory() {
//        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
//        redisConfiguration.setHostName(redisHost);
//        redisConfiguration.setPort(redisPort);
//        return new JedisConnectionFactory(redisConfiguration);
//    }
}

