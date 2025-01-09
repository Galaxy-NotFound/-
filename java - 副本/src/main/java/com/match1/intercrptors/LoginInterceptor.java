package com.match1.intercrptors;

import com.match1.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
        }

        // 令牌验证
        String token = request.getHeader("Authorization");
        System.out.println(token);
        try{
            Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
            response.setStatus(HttpServletResponse.SC_OK);
            // 放行
            return true;
        }catch (Exception e){
            System.out.println(e);
            response.setStatus(401);
            // 不放行
            return false;
        }

    }
}
