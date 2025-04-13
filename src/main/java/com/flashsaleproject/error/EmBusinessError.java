package com.flashsaleproject.error;

/**
 * Enumeration of business error types used across the application.
 * Implements the CommonError interface for uniform error handling.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */
public enum EmBusinessError implements CommonError {

    // 10000 - General errors
    PARAMETER_VALIDATION_ERROR(10001, "Parameter does not validate"),
    UNKNOWN_ERROR(10002, "Unknown error"),

    // 20000 - User-related errors
    USER_NOT_EXIST(20001, "User does not exist"),
    USER_LOGIN_FAIL(20002, "User telephone number or password is not correct"),
    USER_NOT_LOGIN(20003, "User is not logged in"),

    // 30000 - Order-related errors
    STOCK_NOT_ENOUGH(30001, "Item stock is not enough");

    private final int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
