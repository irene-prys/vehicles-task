package demo.task.vehicles.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.embedded.RedisServer;

@Configuration
@PropertySource("classpath:application.yml")
@EnableRedisRepositories(basePackages = "demo.task.vehicles.repositories")
public class AppConfig {
    @Autowired
    private Environment environment;

//    @Bean
//    public RedisConnectionFactory connectionFactory() {
//        return new JedisConnectionFactory();
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(environment.getProperty("spring.redis.host"));
        redisConfiguration.setPort(6379);

        JedisConnectionFactory factory = new JedisConnectionFactory(redisConfiguration);
//        factory.setUsePool(true);
factory.getPoolConfig().setMaxIdle(30);
        factory.getPoolConfig().setMinIdle(30);
        return factory;
    }

//    @Bean
//    public RedisTemplate<?, ?> redisTemplate() {
//        System.out.println("-------");
//        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//        template.setDefaultSerializer(new StringRedisSerializer());
//        template.setEnableTransactionSupport(true);
//        template.setConnectionFactory(connectionFactory());
//        return template;
//    }

//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory());
//        redisTemplate.setExposeConnection(true);
//        // No serializer required all serialization done during impl
////        redisTemplate.setKeySerializer(stringRedisSerializer());
//        //`redisTemplate.setHashKeySerializer(stringRedisSerializer());
////        redisTemplate.setHashValueSerializer(new GenericSnappyRedisSerializer());
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }

//    @Bean
//    public StringRedisSerializer stringRedisSerializer() {
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        return stringRedisSerializer;
//    }

//    @Bean
//    public RedisCacheManager cacheManager() {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
//        redisCacheManager.setTransactionAware(true);
//        redisCacheManager.setLoadRemoteCachesOnStartup(true);
//        redisCacheManager.setUsePrefix(true);
//        return redisCacheManager;
//    }

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory jedisConFactory
//                = new JedisConnectionFactory();
//        jedisConFactory.setHostName("localhost");
//        jedisConFactory.setPort(6379);
//        return jedisConFactory;
//    }



//    Caused by: org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'redisKeyValueTemplate':
//    Cannot resolve reference to bean 'redisKeyValueAdapter' while setting constructor argument;
//    nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'redisKeyValueAdapter':
//    Cannot resolve reference to bean 'redisTemplate' while setting constructor argument;
//    nested exception is org.springframework.beans.factory.BeanCreationException:
//    Error creating bean with name 'redisTemplate' defined in class path resource
//[demo/task/vehicles/configuration/AppConfig.class]:
//    Invocation of init method failed; nested exception is java.lang.IllegalStateException:
//    RedisConnectionFactory is required
//    @Bean
//    public void doSmth(){
////        RedisServerConfiguration
//    }

//    @Bean
//    public LettuceConnectionFactory redisConnectionFactory() {
//
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
//    }
}

