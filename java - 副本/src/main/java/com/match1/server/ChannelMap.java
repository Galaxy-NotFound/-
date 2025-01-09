package com.match1.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.ConcurrentHashMap;

@Data
@Service
public class ChannelMap {

    private static final ConcurrentHashMap<ChannelId, Channel> CHANNEL_MAP = new ConcurrentHashMap<>(128);

    public static ConcurrentHashMap<ChannelId, Channel> getChannelMap() {
        return CHANNEL_MAP;
    }
    public static void addChannel(ChannelId id,Channel channel){
        CHANNEL_MAP.put(id,channel);
    }

    public Channel getChannelById(ChannelId channelId){
        if(CollectionUtils.isEmpty(CHANNEL_MAP)){
            return null;
        }else {
            return CHANNEL_MAP.get(channelId);
        }
    }
    public static void deleteByChannelId(ChannelId channelId){
        CHANNEL_MAP.remove(channelId);
    }



}
