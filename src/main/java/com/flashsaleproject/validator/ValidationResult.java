package com.flashsaleproject.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    //Verify that the results are not incorrect
    private boolean hasError;

    //Store error message map
    private Map<String, String> errorMsgMaps = new HashMap<>();

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMaps() {
        return errorMsgMaps;
    }

    public void setErrorMsgMaps(Map<String, String> errorMsgMaps) {
        this.errorMsgMaps = errorMsgMaps;
    }

    //Implement generic msg method for getting error results from formatted string messages
    public String getErrMsg(){
        return StringUtils.join(errorMsgMaps.values().toArray(), ",");
    }

}
