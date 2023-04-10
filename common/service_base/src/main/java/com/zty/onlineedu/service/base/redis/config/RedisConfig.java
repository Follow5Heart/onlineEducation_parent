package com.zty.onlineedu.service.base.redis.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Author zty
 * @Date 2023/2/6 0:00
 * redis的配置类
 */
@Log4j2
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

    /**
     *  用于创建一个 Redis 缓存管理器（CacheManager）实例。
     * 具体来说，它通过 LettuceConnectionFactory 连接工厂创建了一个 Redis 缓存管理器实例 cacheManager，
     * 并指定了缓存配置 config，该缓存配置包括以下内容：
     * 设置缓存条目的过期时间为 600 秒。
     * 配置 Redis 序列化器，使用 StringRedisSerializer 序列化缓存键，使用 GenericJackson2JsonRedisSerializer 序列化缓存值。
     * 禁用缓存 null 值。
     * 在整个应用程序中，可以使用 cacheManager 实例来管理 Redis 缓存。
     * * 其他组件可以通过依赖注入（Dependency Injection）获取该实例，
     * * 并使用它来读取和写入缓存数据。例如，当需要从数据库中读取数据时，可以首先尝试从 Redis 缓存中获取数据，
     * * 如果缓存中没有，则从数据库中读取数据，并将数据写入 Redis 缓存以供下一次使用。这样可以提高数据读取的性能和响应速度。
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                //过期时间600秒
                .entryTtl(Duration.ofSeconds(600))
                // 配置序列化
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .disableCachingNullValues();

        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

    /**
     * Redis数据操作异常处理，进行自定义配置，如果redis挂了，会绕过redis直接走数据库，反之则会抛出异常
     *
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                log.error("从redis推送数据异常，redis可能挂了");
            }

            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                log.error("从redis获取数据异常，redis可能挂了");
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {

            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {

            }
        };
        return cacheErrorHandler;
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

