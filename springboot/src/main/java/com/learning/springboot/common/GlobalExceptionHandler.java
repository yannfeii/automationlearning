package com.learning.springboot.common;

import com.learning.springboot.dto.ResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServiceException.class})
    public ResultDto serviceExceptionHandler(ServiceException se){
        return responseFormat(se);
    }
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler({Exception.class})
    public ResultDto exceptionHandler(Exception e){
        return responseFormat(e);
    }

    @ExceptionHandler({Throwable.class})
    public ResultDto errorHandler(Throwable t){
        return responseFormat(t);
    }

    public ResultDto responseFormat(Throwable th){
        log.error(th.getMessage());
        String tips = "System is busy, please re-try later";

        if(th instanceof ServiceException){
            return ResultDto.resultFail("Business logic exception!"+tips);
        }else if(th instanceof Exception){
            return ResultDto.resultFail("Non-business logic exception!"+tips);
        }else {
            return ResultDto.resultFail("System exception!"+tips);
        }

    }
}
