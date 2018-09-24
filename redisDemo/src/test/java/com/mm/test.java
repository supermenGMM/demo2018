package com.mm;

import jdk.nashorn.internal.scripts.JD;
import org.junit.Test;
import redis.clients.jedis.*;

import javax.sound.midi.Soundbank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
    @Test
    public void test() {
        Jedis jedis = new Jedis("192.168.55.134");

        jedis.set("foo", "bar");
        System.out.println(jedis.get("foo"));
    }

    /**
     * redis集群
     */
    private String host = "192.168.55.131";
    @Test
    public void test2() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort(host, 7001));
        jedisClusterNodes.add(new HostAndPort(host, 7002));
        jedisClusterNodes.add(new HostAndPort(host, 7003));
//        jedisClusterNodes.add(new HostAndPort(host, 7004));
//        jedisClusterNodes.add(new HostAndPort(host, 7005));
//        jedisClusterNodes.add(new HostAndPort(host, 7006));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        jedisCluster.set("foo", "bar");
        System.out.println(jedisCluster.get("foo"));
    }

    /**
     * JedisPool
     */
    @Test
    public void test3() {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host);
         Jedis jedis = jedisPool.getResource();
        jedis.sadd("ball", "football");
        List<String> ball = jedis.lrange("ball", 0, -1);

        jedisPool.close();



    }


}

