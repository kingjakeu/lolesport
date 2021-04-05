package com.kingjakeu.promode.common.exception;

import com.kingjakeu.promode.common.constant.CommonError;
import org.springframework.http.HttpStatus;

public class CommonException extends RuntimeException{
    protected final CommonError commonError;
    protected final HttpStatus httpStatus;

    public CommonException(HttpStatus httpStatus, CommonError commonError){
        super(commonError.getMessage());
        this.httpStatus = httpStatus;
        this.commonError = commonError;
    }

    public CommonException(HttpStatus httpStatus, CommonError commonError, String additiveErrorMessage){
        super(commonError.getMessage() + additiveErrorMessage);
        this.httpStatus = httpStatus;
        this.commonError = commonError;
    }
}