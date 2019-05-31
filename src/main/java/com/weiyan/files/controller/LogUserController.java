package com.weiyan.files.controller;

import com.weiyan.files.auto.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.controller
 * @date 2019/5/31 16:06
 */
@RestController
@CrossOrigin
@RequestMapping("user")
public class LogUserController {
    @Autowired
    private JwtUtil jwtUtil;

    String userName = "admin";
    String password = "admin";

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map login(@RequestBody  Map map) {
        Map resultMap = new HashMap();
        String userName = (String) map.get("userName");
        String password = (String) map.get("password");

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            resultMap.put("flag", false);
            resultMap.put("message", "用户名密码不能为空~");
            return resultMap;
        }
         if (userName.equals(this.userName) && password.equals(this.password)){
             String jwt = jwtUtil.createJWT(this.userName, "files", "system");
             resultMap.put("flag", true);
             resultMap.put("message", "登陆成功!");
             resultMap.put("auth",jwt);
         }else{
             resultMap.put("flag", false);
             resultMap.put("message", "用户名密码错误~");
         }

        return resultMap;

    }

}
