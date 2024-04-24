package com.zym.dpan.exception;

import com.zym.dpan.utils.BizCodeEnum;
import com.zym.dpan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DpanControllerAdvice
 * Package: com.zym.dpan.exception
 *
 * @Author zym
 * @Create 2024/4/7 17:54
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.zym.dpan.app")
public class DpanControllerAdvice {
    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e){
        log.error("参数校验出错：{}，异常类型：{}",e.getMessage(),e.getClass());
        // 异常详细信息
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> data = new HashMap<>();
        bindingResult.getFieldErrors().forEach((item)->{
            String field = item.getField();
            String msg = item.getDefaultMessage();
            data.put(field,msg);
        });
        return R.fail(BizCodeEnum.VALID_EXCEPTION.getCode(),BizCodeEnum.VALID_EXCEPTION.getMsg(),data);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable t){
        log.error("发生未知异常：{}，异常类型：{}",t.getMessage(),t.getClass());
        return R.fail(BizCodeEnum.UNKNOW_EXCEPTION.getCode(),BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
