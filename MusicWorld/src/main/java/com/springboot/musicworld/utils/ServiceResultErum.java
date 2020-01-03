package com.springboot.musicworld.utils;

/**
 * 服务枚举
 */
public enum ServiceResultErum {

    SUCCESSA("SUCCESS"),
    failureB("failure"),
    SUCCESS("成功"),
    NotEmpty("不能为空"),
    failure("失败");



    private String result;

    ServiceResultErum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
