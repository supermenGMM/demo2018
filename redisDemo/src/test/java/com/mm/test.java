package com.mm;

import org.junit.Test;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
    @Test
    public void test() {
        Jedis jedis = new Jedis("10.3.200.4");
//        Jedis jedis = new Jedis("192.168.55.134");

        jedis.set("a", "bar");
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
     * 类似于事物。批量执行命令，在管道流同步前获取数据集，在管道流同步之后才能get数据。是futuer的一种
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
     * 发布订阅
     */
    @Test
    public void pubsub() {
        Jedis jedis = new Jedis(host2);
        MyListener listener = new MyListener();
        jedis.subscribe(listener,"channel1");

    }

    /**
     * shardedJedis
     *  //1.direct connection
     */
    // TODO 用shard赋值。如果已经有key a:12,那么赋值a:foo 就不能成功。为什么。
    @Test
    public void shaardedJedisByShard() {
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo(host2, 6379);
//        si.setPassword("foobared");

        JedisShardInfo si2 = new JedisShardInfo(host2, 6380);
//        si2.setPassword("foobared");

        list.add(si);
        list.add(si2);


        ShardedJedis jedis = new ShardedJedis(list,ShardedJedis.DEFAULT_KEY_TAG_PATTERN);//? TODO 不明白怎么用。什么意思.Force certain keys to go to the same shard
        jedis.set("foo{bar}", "12345");// TODO
        jedis.set("car{bar}", "67890");

        JedisShardInfo jedisShardInfo = jedis.getShardInfo("a");
        System.out.println(jedisShardInfo.getHost()+","+jedisShardInfo.getPort()
        +","+jedisShardInfo.getPassword()+","+jedisShardInfo.getConnectionTimeout()
        +","+jedisShardInfo.getName()+"====");
        jedis.set("a", "foo");

        String a = jedis.get("a");
        System.out.println(a);
        jedis.disconnect();


    }

    /**
     * shardedJedis
     *  // 2. pooled connection
     */
    @Test
    public void shaardedJedisByPool() {
        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        JedisShardInfo si = new JedisShardInfo(host2, 6379);
//        si.setPassword("foobared");

        JedisShardInfo si2 = new JedisShardInfo(host2, 6380);
//        si2.setPassword("foobared");

        list.add(si);
        list.add(si2);


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        ShardedJedisPool pool = new ShardedJedisPool(jedisPoolConfig, list);
        try (ShardedJedis jedis1 = pool.getResource()){
            JedisShardInfo jedisShardInfo = jedis1.getShardInfo("a");
            System.out.println(jedisShardInfo.getHost()+","+jedisShardInfo.getPort()
                    +","+jedisShardInfo.getPassword()+","+jedisShardInfo.getConnectionTimeout()
                    +","+jedisShardInfo.getName()+"====");
            jedis1.set("a", "foo222");
        }
        try (ShardedJedis jedis2 = pool.getResource()){
            JedisShardInfo jedisShardInfo = jedis2.getShardInfo("a");
            System.out.println(jedisShardInfo.getHost()+","+jedisShardInfo.getPort()
                    +","+jedisShardInfo.getPassword()+","+jedisShardInfo.getConnectionTimeout()
                    +","+jedisShardInfo.getName()+"====");
            jedis2.set("z", "bar");
        }
        pool.close();
    }

    /**
     * 主从
     */
    @Test
    public void masterSlave() {
        Jedis jedis = new Jedis(host2);
        String slaveof = jedis.slaveof(host2, 6380);
        System.out.println(slaveof);
        jedis.slaveofNoOne();
        jedis.set("mm", "zhaomeng");
        jedis.close();
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

