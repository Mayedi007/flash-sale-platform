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

/**
 * Base controller for common functionalities like global exception handling.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */

public class BaseController {

    // Define form content type constant
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";

    /**
     * Global exception handler to catch uncaught exceptions in controller layer.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception ex) {
        Map<String, Object> responseData = new HashMap<>();

        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        } else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }

        return CommonReturnType.create(responseData, "fail");
    }
}
