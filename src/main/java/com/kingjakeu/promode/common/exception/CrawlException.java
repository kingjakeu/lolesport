package com.kingjakeu.promode.common.exception;

import com.kingjakeu.promode.common.constant.CommonError;
import org.springframework.http.HttpStatus;

public class CrawlException extends CommonException{
    public CrawlException(CommonError commonError){
        super(HttpStatus.INTERNAL_SERVER_ERROR, commonError);
    }
}
