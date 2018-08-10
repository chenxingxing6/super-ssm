package io.renren;

import com.alibaba.fastjson.JSON;
import io.renren.modules.robot.entity.SendResult;
import io.renren.modules.robot.message.Message;
import io.renren.modules.robot.message.TextMessage;
import io.renren.modules.robot.service.DingtalkChatbotClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 钉钉机器人测试-TextMessage
 * Created by lanxinghua on 2018/8/3.
 */
public class TextMessageTest extends BaseTest{

    @Resource
    private DingtalkChatbotClient dingtalkChatbotClient;

    @Test
    public void sendTextMessageTest(){
        Message message = new TextMessage("大家好!");
        SendResult result = dingtalkChatbotClient.send(WEBHOOK, message);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void sendTextMessageWithAt(){
        TextMessage message = new TextMessage("大家好");
        List<String> atMobies = new ArrayList<>();
        atMobies.add("18379643981");
        message.setAtMobiles(atMobies);
        SendResult result = dingtalkChatbotClient.send(WEBHOOK, message);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void sendTextMessageWithAtAll(){
        TextMessage message = new TextMessage("大家好");
        message.setIsAtAll(true);
        SendResult result = dingtalkChatbotClient.send(WEBHOOK, message);
        System.out.println(JSON.toJSONString(result));
    }




}