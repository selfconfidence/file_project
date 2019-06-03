package com.weiyan.files.controller;

import com.weiyan.files.auto.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("/index")
    public String homeIndex(){
        return "login";
    }

    @GetMapping("/view")
    public String homeView(HttpServletRequest request){
        String auth = request.getParameter("auth");
        try {
            Claims claims = jwtUtil.parseJWT(auth);
            String id = claims.getId();
            if ("admin".equals(id)) {
                return "view";
            }else{
                return "login";
            }
        }catch (Exception e){
            return "login";
        }



    }
}
