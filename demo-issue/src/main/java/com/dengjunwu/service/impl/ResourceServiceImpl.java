package com.dengjunwu.service.impl;

import com.dengjunwu.dao.Log;
import com.dengjunwu.dao.LogDao;
import com.dengjunwu.exception.RestResponseStatus;
import com.dengjunwu.exception.ServerException;
import com.dengjunwu.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private LogDao logDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void initResource(Integer i) {
        System.out.println(Thread.currentThread().getName() + ":" + i);
        Integer num = 0;
        try {
            logDao.save(Log.builder().type(i + "").userId(1).wxId(1).build());
            int result = i / num;
            System.out.println("result:" + result);
        }catch (Exception e){
            System.out.println("exception:" + e);
            throw new ServerException(RestResponseStatus.COMMON_ERROR_STATUS, "调用失败");
        }
    }
}
