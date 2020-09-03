package com.square.health.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {
    @Bean
    public JedisConnectionFactory connectionFactory() {     // It will create filter for Redis store which will override default Tomcat Session

        JedisConnectionFactory  connectionFactory= new JedisConnectionFactory();
        connectionFactory.getPoolConfig().setMaxIdle(30);
        connectionFactory.getPoolConfig().setMinIdle(10);
        return connectionFactory;
    }
}