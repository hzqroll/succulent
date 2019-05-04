package com.roll.succulent.bee.worker.util;

import com.roll.succulent.bee.worker.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zongqiang.hao
 * created on 2019-04-29 16:33.
 */
public class SessionUtil {
    private static final Map<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        channelMap.put(session.getUserId(), channel);
        channel.attr(Session.getSessionAttr()).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            channelMap.remove(session.getUserId());
            channel.attr(Session.getSessionAttr()).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Session.getSessionAttr()).get();
    }

    public static Channel getChannel(String userId) {
        return channelMap.get(userId);
    }
}
