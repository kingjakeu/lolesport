package com.kingjakeu.promode.common.exception;

import com.kingjakeu.promode.common.constant.CommonError;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CommonException{
    public ResourceNotFoundException(CommonError commonError){
        super(HttpStatus.NOT_FOUND, commonError);
    }
}
