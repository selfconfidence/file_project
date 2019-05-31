package com.weiyan.files.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.webflux
 * @date 2019/5/30 16:34
 */
//首页
@Controller
public class FileHomeController {
    @GetMapping("/index")
    public String homeIndex(){
        return "login";
    }
}
