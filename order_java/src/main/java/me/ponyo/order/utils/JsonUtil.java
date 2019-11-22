package me.ponyo.order.utils;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


/**
 * @ Author: Ding <br/>
 * @ Version: V1.0
 * @ Notes: Json工具类
 * <p>
 * Created with IDEA. Date：2019/11/22 4:34 下午
 * <a href="https://github.com/YooDing">Github Home Page</a>
 * </p>
 */
@Slf4j
public class JsonUtil implements Serializable {

    /**
     * 序列号id
     */
    private static final long serialVersionUID = 1L;

    /**
     * response相应输出json字符串
     * @param response
     * @param data 返回数据实体
     */
    public static void outJson(HttpServletResponse response, Object data) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJSONString(data));
        } catch (IOException e) {
            log.error("response相应输出json字符串发生异常 :" + e.getMessage());
        }
    }
}
