package com.angelo.util;

import com.angelo.domain.Result;

public class MessageUtil {

    /**
     * 成功方法
     * @param object
     * @return
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMessage("SUCCESS");
        result.setData(object);

        return result;
    }

    /**
     * 成功但是
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 失败方法
     * @param code
     * @param message
     * @return
     */
    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);

        return result;
    }
}
