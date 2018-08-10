package io.renren.modules.robot.entity;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lanxinghua
 * @date 2018/08/04 14:40
 * @description
 */
public class SendResult {
    private boolean isSuccess;
    private Integer errorCode;
    private String errorMsg;

    public SendResult(){
        this.isSuccess=false;
        this.errorCode=0;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        Map<String,Object> items = new HashMap<>();
        items.put("errorCode",errorCode);
        items.put("errorMsg",errorMsg);
        items.put("isSuccess",isSuccess);
        return JSON.toJSONString(items);
    }
}