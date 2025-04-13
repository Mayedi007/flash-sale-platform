package com.flashsaleproject.error;

/**
 * Wrapper class for handling business-level exceptions.
 * Implements a standard interface for error codes and messages.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */
public class BusinessException extends Exception implements CommonError {

    private final CommonError commonError;

    /**
     * Constructor accepting a predefined CommonError (e.g., EmBusinessError).
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * Constructor with custom error message overriding the default.
     */
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
