package com.angelo.handle;

import com.angelo.domain.Result;
import com.angelo.exception.UserException;
import com.angelo.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        logger.info("进入error");

        // 是否属于自定义异常
        if (e instanceof UserException) {
            UserException userException = (UserException) e;

            return MessageUtil.error(userException.getCode(), userException.getMessage());
        } else {
            logger.error("系统异常 {}", e);

            return MessageUtil.error(1000, "系统异常!");
        }
    }
}
