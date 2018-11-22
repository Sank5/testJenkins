package com.sank.utils.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Configuration
public class JedisClusterConfig {

	@Autowired
    private RedisProperties redisProperties;

    public JedisCluster getJedisCluster(){
        String [] serverArray=redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> nodes=new HashSet<>();

        for (String ipPort:serverArray){
            String [] ipPortPair=ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));

        }
        JedisCluster s = new JedisCluster(nodes,redisProperties.getCommandTimeout());
        return  s;
    }
}
