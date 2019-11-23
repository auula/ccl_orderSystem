package me.ponyo.order.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: 统一返回结果
 * <p>
 * Created with IDEA. Date：2019/11/22 9:01 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
public class BaseResult implements Serializable {

    private Integer status;
    private String message;
    private Map<String, Object> data;

    public BaseResult build(Integer s, String mes) {
        this.message = mes;
        this.status = s;
        this.data = new HashMap<>();
        return this;
    }

    public BaseResult add(String key, Object obj) {
        this.data.put(key, obj);
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getResult() {
        return data;
    }

    public void setResult(Map<String, Object> result) {
        this.data = result;
    }
}
