package io.renren.modules.robot.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.renren.modules.robot.entity.SendResult;
import io.renren.modules.robot.message.Message;
import io.renren.modules.robot.service.DingtalkChatbotClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 * @author lanxinghua
 * @date 2018/08/04 14:49
 * @description
 */
@Service
public class DingtalkChatbotClientImpl implements DingtalkChatbotClient {
    //获取httpclient
    private HttpClient getHttpclient() {
        return HttpClients.createDefault();
    }

    @Override
    public SendResult send(String webhook, Message message) {
        SendResult sendResult = new SendResult();
        try {
            HttpClient httpclient = getHttpclient();
            HttpPost httppost = new HttpPost(webhook);
            httppost.addHeader("Content-Type", "application/json; charset=utf-8");
            StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
            httppost.setEntity(se);
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = JSONObject.parseObject(result);
                Integer errcode = obj.getInteger("errcode");
                sendResult.setErrorCode(errcode);
                sendResult.setSuccess(errcode.equals(0));
                sendResult.setErrorMsg(obj.getString("errmsg"));
            }
            return sendResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}