package com.dengjunwu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dengjunwu.http.HttpClientUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Slf4j
@Component
public class RequestTemplate {

    public JSONObject initSmartLive() {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("type", "1");
        paramMap.put("sn", "123456");
        paramMap.put("channelNo", "0");
        String initUrl = appendParamGetMethod("https://spare.cn.miaozhen.com/api/live/create", paramMap);
        try {
            JSONObject jsonObject = HttpClientUtil
                    .buildGetRequest(initUrl)
                    .addHeader("token", "token")
                    .executeToJson();
//            Integer code = jsonObject.getInteger("code");
//            Integer data = jsonObject.getInteger("data");
//            String codeText = jsonObject.getString("codeText");
            log.info("result:{}", JSONObject.toJSONString(jsonObject));
            return jsonObject;
        } catch (Exception e) {
            log.error("{}, sn:{}, channelNo:{}", "初始化roomId失败");
            return null;
        }
    }

    public String appendParamGetMethod(String baseUrl, Map<String, Object> paramMap) {
        if (CollectionUtils.isEmpty(paramMap)) {
            return baseUrl;
        }
        StringBuilder sb = new StringBuilder(baseUrl);
        sb.append("?");
        paramMap.forEach((key, value) -> {
            sb.append(key).append("=").append(value).append("&");
        });
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
