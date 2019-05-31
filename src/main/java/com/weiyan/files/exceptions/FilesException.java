package com.weiyan.files.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.exceptions
 * @date 2019/5/31 9:31
 */
@Component
public class FilesException {
    @ExceptionHandler(Exception.class)
    public Map fileException(Exception e){
       Map result = new HashMap();
       result.put("flag",false);
       result.put("message",e.getMessage());
       return result;
    }
}
