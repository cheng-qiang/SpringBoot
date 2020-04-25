package com.chen.thylealeaf.security.exception;

import com.chen.thylealeaf.security.model.RespBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 程强
 * @date 2020年04月23日 13:58
 * @Description:
 */
@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public RespBean exceptionHandler(Exception e){
        if (e instanceof UsernameNotFoundException){
            return RespBean.error(e.getMessage());
        }
        return RespBean.error("服务端异常");
    }

}
