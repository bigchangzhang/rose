package com.yixiang.rose.common.utils;
import java.io.Serializable;

/**
 * 数据返回响应体
 */
public class ResultModel implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 状态代码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数量
     */
    private long count;
    /**
     * 数据
     */
    private Object data;

    public ResultModel() {
    }

    public ResultModel(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultModel(int code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultModel(ResultModel model) {
        this.code = model.getCode();
        this.msg = model.getMsg();
        this.count = model.getCount();
        this.data = model.getData();
    }

    public ResultModel set(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResultModel set(int code, String msg, int count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public long getCount() {
        return count;
    }
    public void setCount(long count) {
        this.count = count;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}
