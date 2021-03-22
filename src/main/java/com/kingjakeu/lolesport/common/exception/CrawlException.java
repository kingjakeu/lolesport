package com.kingjakeu.lolesport.common.exception;

import com.kingjakeu.lolesport.common.constant.CommonError;
import org.springframework.http.HttpStatus;

public class CrawlException extends CommonException{
    public CrawlException(CommonError commonError){
        super(HttpStatus.INTERNAL_SERVER_ERROR, commonError);
    }
}
