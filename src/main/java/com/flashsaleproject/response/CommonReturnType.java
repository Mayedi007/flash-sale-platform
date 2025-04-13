package com.flashsaleproject.response;

public class CommonReturnType {
//Indicates ‘success’ or ‘fail’ for the corresponding request.
    private String status;

    //status=success, data returns the data required by the front-end
    //status=fail, data uses the generic error code format
    private Object data;

    public static CommonReturnType creat(Object result){
        return CommonReturnType.creat(result,"success");
    }

    public static CommonReturnType creat(Object result, String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
