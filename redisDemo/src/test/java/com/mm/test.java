package com.mm;

import javafx.print.PageOrientation;
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
        Jedis jedis = new Jedis("10.3.200.4");
//        Jedis jedis = new Jedis("192.168.55.134");

        jedis.set("foo", "bar");
        System.out.println(jedis.get("foo"));
        jedis.close();
    }

    /**
     * transaction
     */
    @Test
    public void transactionTest() {
        Jedis jedis = new Jedis("10.3.200.4");
//        Jedis jedis = new Jedis("192.168.55.134");
//        jedis.incr("a");
        System.out.println("前"+jedis.get("a"));
        jedis.watch("a");
        Transaction multi = jedis.multi();
        multi.incr("a");
//        multi.exec();
        multi.discard();
        System.out.println("后"+jedis.get("a"));
    }

    /**
     * 获取事物的返回结果
     */
    @Test
    public void transactionTest2() {
        Jedis jedis = new Jedis(host2);
        Transaction t = jedis.multi();
        t.set("foo", "bar");
        Response<String> result1 = t.get("foo");
        t.zadd("ball1", 1, "foot");
        t.zadd("ball1", 2, "basket");
        t.zadd("ball1", 3, "pingpang");
        Response<Set<String>> ball = t.zrange("ball1", 0, -1);

        t.exec();
        String s = result1.get();
        System.out.println(s);
        int size = ball.get().size();
        System.out.println("size:"+size);
    }

    /**
     * 事物，Redis does not allow to use intermediate results of a transaction within that same transaction. This does not work:
     * // this does not work! Intra-transaction dependencies are not supported by Redis!
     */
    @Test
    public void transactionTest3() {
        Jedis jedis = new Jedis(host2);
        jedis.watch("a");
        Transaction t = jedis.multi();
        System.out.println(t.get("a"));//输出  Response string
        if(t.get("a").equals("1")){//不能这么写。等到的结果不是a真正的值。
            t.set("b", "1");
        }else
            t.set("c","1");
        t.exec();
    }

    /**
     * pipelining
     */
    @Test
    public void pipelining() {
        Jedis jedis = new Jedis(host2);
        Pipeline pipelined = jedis.pipelined();
        pipelined.set("q", "3");
        pipelined.zadd("sing", 1, "rock");
        pipelined.zadd("sing", 3, "popular");
        pipelined.zadd("sing", 3, "classic");



        Response<String> q = pipelined.get("q");
        Response<Set<String>> sing = pipelined.zrange("sing", 0, -1);
        pipelined.sync();
        System.out.println(q.get());
        System.out.println(sing.get().size());
    }

    /**
     * redis集群
     */
    private String host = "192.168.55.131";
    private String host2 = "10.3.200.4";

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
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host2);
         Jedis jedis = jedisPool.getResource();
        jedis.sadd("ball", "football");
        List<String> ball = jedis.lrange("ball", 0, -1);

        jedisPool.close();



    }


}

