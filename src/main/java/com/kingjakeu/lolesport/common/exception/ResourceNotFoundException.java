package com.kingjakeu.lolesport.common.exception;

import com.kingjakeu.lolesport.common.constant.CommonError;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends CommonException{
    public ResourceNotFoundException(CommonError commonError){
        super(HttpStatus.NOT_FOUND, commonError);
    }
}
