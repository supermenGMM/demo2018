package com.mm.test;

import redis.clients.jedis.JedisPubSub;

public class MyListener extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {

        super.onMessage(channel, message);
        System.out.println("nmessage:"+channel+","+message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        super.onPMessage(pattern, channel, message);
        System.out.println("pnmessage:"+channel+","+message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        super.onSubscribe(channel, subscribedChannels);
        System.out.println("subscribe:"+"渠道:"+channel+","+subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        super.onUnsubscribe(channel, subscribedChannels);
        System.out.println("unsubscribe:"+"渠道:"+channel+","+subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        super.onPUnsubscribe(pattern, subscribedChannels);
        System.out.println("psubscribe:"+"渠道:"+pattern+","+subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        super.onPSubscribe(pattern, subscribedChannels);
        System.out.println("punsubscribe:"+"渠道:"+pattern+","+subscribedChannels);
    }
}
