package com.dengjunwu.exception;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

public enum RestResponseStatus {
    //200是成功的代码
    SUCCESS_STATUS(200, "OK"),

    //通用
    NOT_LOGIN(40000, "请登录后重试"),
    PARAM_ERROR(40001, "参数错误"),
    NUMBER_FORMAT_ERROR(40002, "数字格式错误"),
    ENUM_ERROR(40003, "枚举错误"),
    RESOURCE_NOT_FOUND(40040, "实体无法找到错误"),
    CANT_DELETE(40005, "不能删除实体"),
    NOT_ENOUGH_AUTH(40006, "没有权限进行该项操作"),
    HTTP_CANT_REACH(40007, "调用第三方http服务时,服务不可用"),
    RESOURCE_STATUS_ERROR(40008, "资源状态错误"),
    ACCESS_LIMIT_STATUS(41275, "访问次数受限"),
    COMMON_ERROR_STATUS(50000, "服务器错误"),
    DATABASE_OPERATION_ERROR(40009, "数据库操作错误"),
    RESOURCE_DUPLICATED(40011, "实体出现重复"),
    FREQUENCY_TOO_HIGH(40012, "频率过高"),

    ;

    private int status;
    private String desc;

    private volatile static Map huanshanResponseMap = null;

    public static Map<Integer, RestResponseStatus> getHuashanResponseMap() {
        if (huanshanResponseMap == null) {
            synchronized (RestResponseStatus.class) {
                if (huanshanResponseMap == null) {
                    Map<Integer, RestResponseStatus> responseStatusMap = Maps.newHashMap();
                    for (RestResponseStatus restResponseStatus : RestResponseStatus.values()) {
                        if (restResponseStatus.name().startsWith("HUASHAN_CODE")) {
                            responseStatusMap.put(restResponseStatus.getStatus(), restResponseStatus);
                        }
                    }
                    huanshanResponseMap = ImmutableMap.<Integer, RestResponseStatus>builder()
                            .putAll(responseStatusMap).build();
                }
            }
        }
        return huanshanResponseMap;
    }

    RestResponseStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static RestResponseStatus get(int status) {
        for (RestResponseStatus restResponseStatus : RestResponseStatus.values()) {
            if (restResponseStatus.getStatus() == status) {
                return restResponseStatus;
            }
        }
        throw new ResourceNotFoundException("错误码不存在" + status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
