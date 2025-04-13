package com.flashsaleproject.controller;

import com.flashsaleproject.error.BusinessException;
import com.flashsaleproject.error.EmBusinessError;
import com.flashsaleproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
//    public Object handlerException(HttpServletRequest request, Exception ex){
//        Map<String, Object> responseDate = new HashMap<>();
//
//        if(ex instanceof BusinessException){
//            BusinessException businessException = (BusinessException) ex;
//
//            responseDate.put("errCode", businessException.getErrCode());
//            responseDate.put("errMsg", businessException.getErrMsg());
//        }else{
//            responseDate.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
//            responseDate.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
//        }
//
//        return CommonReturnType.creat(responseDate,"fail");
//    }
}
