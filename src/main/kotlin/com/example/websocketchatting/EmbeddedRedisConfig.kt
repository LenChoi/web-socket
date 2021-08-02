package com.example.websocketchatting


import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Profile("local")
@Configuration
class EmbeddedRedisConfig {
    private val redisHost = "127.0.0.1"
    private val redisPort =  6379

    private lateinit var redisServer:RedisServer

    @PostConstruct
    fun redisServer() {
        redisServer = RedisServer(redisPort)
        redisServer.start()
    }

    @PreDestroy
    fun stopRedis(){
        redisServer.stop()
    }
}