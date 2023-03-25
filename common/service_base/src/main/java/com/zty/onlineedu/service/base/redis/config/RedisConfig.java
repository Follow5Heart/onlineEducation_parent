package com.zty.onlineedu.service.base.redis.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author zty
 * @Date 2023/2/6 0:00
 * redis的配置类
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //参照StringRedisTemplate内部实现指定序列化器
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer());
        redisTemplate.setHashKeySerializer(keySerializer());
        redisTemplate.setValueSerializer(valueSerializer());
        redisTemplate.setHashValueSerializer(valueSerializer());
        return redisTemplate;
    }

    private RedisSerializer<String> keySerializer(){
        return new StringRedisSerializer();
    }

    //使用Jackson序列化器
    private RedisSerializer<Object> valueSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
    /*
    //**集群版写法***
    private final Environment environment;

    public RedisConfig(Environment environment) {
        this.environment = environment;
    }

    *//**
     * 配置lettuce连接池
     *
     * @return
     *//*
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.cluster.lettuce.pool")
    public GenericObjectPoolConfig redisPool() {
        return new GenericObjectPoolConfig();
    }

    *//**
     * 配置第一个数据源的
     *
     * @return
     *//*
    @Bean("redisClusterConfig")
    @Primary
    public RedisClusterConfiguration redisClusterConfig() {

        Map<String, Object> source = new HashMap<>(8);
        source.put("spring.redis.cluster.nodes", environment.getProperty("spring.redis.cluster.nodes"));
        RedisClusterConfiguration redisClusterConfiguration;
        redisClusterConfiguration = new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
        redisClusterConfiguration.setPassword(environment.getProperty("spring.redis.password"));
        return redisClusterConfiguration;

    }

    *//**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     *
     * @param redisPool
     * @param redisClusterConfig
     * @return
     *//*
    @Bean("lettuceConnectionFactory")
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig redisPool, @Qualifier("redisClusterConfig") RedisClusterConfiguration redisClusterConfig) {
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPool).build();
        return new LettuceConnectionFactory(redisClusterConfig, clientConfiguration);
    }

    *//**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory 的 RedisConnectionFactory
     * 并且标识第一个数据源是默认数据源 @Primary
     *
     * @param redisConnectionFactory
     * @return
     *//*
    @Bean("redisTemplate")
    @Primary
    public RedisTemplate redisTemplate(@Qualifier("lettuceConnectionFactory") RedisConnectionFactory redisConnectionFactory) {
        return getRedisTemplate(redisConnectionFactory);

    }

    //@Bean
    //@SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> getRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJson2JsonRedisSerializer serializer = new FastJson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(serializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();
        return template;
    }*/
}

