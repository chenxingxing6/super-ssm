package io.renren.modules.job.task;

import io.renren.common.exception.BizException;
import io.renren.common.exception.RRException;
import io.renren.modules.robot.message.*;
import io.renren.modules.robot.service.DingtalkChatbotClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author lanxinghua
 * @date 2018/08/04 14:15
 * @description 钉钉机器人测试
 */
@Component("dDRobotTask")
public class DDRobotTask {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private DingtalkChatbotClient dingtalkChatbotClient;

    public void sendTextMessage(String param){
        try {
            JSONObject json = JSONObject.fromObject(param);
            String webhock = json.getString("webhock");
            Message message = new TextMessage(json.getString("message"));
            JSONArray array = JSONArray.fromObject(json.getString("toUser"));
            if (array.size()==0){
                ((TextMessage) message).setIsAtAll(true);
            }else {
               ((TextMessage) message).setAtMobiles(JSONArray.toList(array,new String(),new JsonConfig()));
            }
            dingtalkChatbotClient.send(webhock,message);
            logger.info("发送成功！sendTextMessage");
        } catch (Exception e) {
            throw new BizException("数据填写有误!",0);
        }
    }

    public void sendLinkMessage(String param){
        try {
            LinkMessage message = new LinkMessage();
            JSONObject json = JSONObject.fromObject(param);
            message.setTitle(json.getString("title"));
            message.setText(json.getString("text"));
            if (param.contains("picUrl")){
                message.setPicUrl(json.getString("picUrl"));
            }
            message.setMessageUrl(json.getString("messageUrl"));
            String webhock = json.getString("webhock");
            dingtalkChatbotClient.send(webhock,message);
            logger.info("发送成功！sendLinkMessage");
        } catch (Exception e) {
            throw new BizException("数据填写有误!",1);
        }
    }

    public void sendMarkdownMessage(String param){
        try {
            MarkdownMessage message = new MarkdownMessage();
            JSONObject json = JSONObject.fromObject(param);
            message.setTitle(json.getString("title"));
            message.add(json.getString("text"));
            String webhock = json.getString("webhock");
            dingtalkChatbotClient.send(webhock,message);
            logger.info("发送成功！sendMarkdownMessage");
        } catch (Exception e) {
            throw new BizException("数据填写有误!",1);
        }
    }

    public void sendActionCardMessage(String param){
        try {
            SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
            JSONObject json = JSONObject.fromObject(param);
            String webhock = json.getString("webhock");
            message.setTitle(json.getString("title"));
            message.setHideAvatar(json.getString("hideAvatar").equals("0")?false:true);
            message.setBriefText(json.getString("text"));
            message.setSingleTitle(json.getString("singleTitle"));
            message.setSingleURL(json.getString("singleURL"));
            dingtalkChatbotClient.send(webhock,message);
            logger.info("发送成功！sendActionCardMessage");
        } catch (Exception e) {
            throw new BizException("数据填写有误!",1);
        }
    }

    public void sendFeedCardMessage(String param){
        try {
            FeedCardMessage message = new FeedCardMessage();
            List<FeedCardMessageItem> items = new ArrayList<FeedCardMessageItem>();
            JSONObject json = JSONObject.fromObject(param);
            String webhock = json.getString("webhock");
            JSONArray array = JSONArray.fromObject(json.getString("links"));
            for (int i = 0; i <array.size() ; i++) {
                FeedCardMessageItem item1 = new FeedCardMessageItem();
                JSONObject jsonObject = array.getJSONObject(i);
                item1.setTitle(jsonObject.getString("title"));
                item1.setPicURL(jsonObject.getString("picURL"));
                item1.setMessageURL(jsonObject.getString("messageURL"));
                items.add(item1);
            }
            message.setFeedItems(items);
            dingtalkChatbotClient.send(webhock,message);
            logger.info("发送成功！sendFeedCardMessage");
        } catch (Exception e) {
            throw new BizException("数据填写有误!",1);
        }
    }
}