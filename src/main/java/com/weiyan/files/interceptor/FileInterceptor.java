package com.weiyan.files.interceptor;

import com.weiyan.files.auto.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mister_wei
 * @version 1.1.1
 * @title web_service
 * @package com.weiyan.files.interceptor
 * @date 2019/5/31 17:46
 */
@Component
public class FileInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Logger logger = LoggerFactory.getLogger(FileInterceptor.class);
        String auth = request.getHeader("AUTH");
        try {
            Claims claims = jwtUtil.parseJWT(auth);
            String id = claims.getId();
            if ("admin".equals(id)){
                request.setAttribute("admin",id);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            return true;
        }

        return true;
    }
}
