package com.dengjunwu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dengjunwu.dao.Log;
import com.dengjunwu.dao.LogDao;
import com.dengjunwu.exception.RestResponseStatus;
import com.dengjunwu.exception.ServerException;
import com.dengjunwu.service.NvrService;
import com.dengjunwu.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class NvrServiceImpl implements NvrService {
    @Resource
    private LogDao logDao;

    @Resource
    private RequestTemplate requestTemplate;

    @Resource
    private ResourceService resourceService;

    @Override
    @Async("taskExecutor")
//    @Transactional(rollbackFor = Exception.class)
    public void initResource() {
        for (int i =0 ; i <= 8; i ++){
            try {
                this.initNvrResource(i);
//                resourceService.initResource(i);
            }catch (Exception e){
                System.out.println("exception happened : " + e.getMessage());
            }
        }
    }

//    @Transactional(rollbackFor = Exception.class)
    public void  initNvrResource(int i){
        System.out.println(Thread.currentThread().getName() + ":" + i);
        Integer num = 0;
        JSONObject result = requestTemplate.initSmartLive();
        if (result == null){
            log.info("init smart live failure:{}", i);
            throw new ServerException(RestResponseStatus.COMMON_ERROR_STATUS, "初始化资源失败");
        }else {
            logDao.save(Log.builder().type(i + "").userId(1).wxId(1).build());
            int  r = i / num;
            log.info("r:{}", r);
            throw new ServerException(RestResponseStatus.COMMON_ERROR_STATUS, "调用失败");
        }
    }
}
