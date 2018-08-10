package io.renren.modules.robot.service;

import io.renren.modules.robot.entity.SendResult;
import io.renren.modules.robot.message.Message;

/**
 * @author lanxinghua
 * @date 2018/08/04 14:48
 * @description 钉钉机器人发送消息
 */
public interface DingtalkChatbotClient {
    /**
     * @param webhook 机器人url
     * @param message 发送的消息
     * @return
     */
    public SendResult send(String webhook, Message message);
}