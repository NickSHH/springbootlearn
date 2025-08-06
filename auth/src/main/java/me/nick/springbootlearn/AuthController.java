package me.nick.springbootlearn;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class AuthController {
    private static String key = "1234";

    @GetMapping("/login")
    public String login(String username, String password) {
 
        // 验证用户名和密码
        // ...
 
        // 登录成功
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("uid", username.equals("admin") ? 1 : 2);
        map.put("role", username.equals("admin") ? "admin" : "employee");
 
        return JWTUtil.createToken(map, key.getBytes());
    }

    @GetMapping("/validateToken")
    public Integer validateToken(@RequestHeader String token) {
        final JWT jwt = JWTUtil.parseToken(token);
        return ((NumberWithFormat)jwt.getPayload("uid")).intValue();
    }
    
    @PostMapping("/validateUrl")
    public String validateUrl(@RequestBody Map<String, Object> validateInfo) {
        // Integer uid = (Integer) validateInfo.get("uid");
        // String url = (String) validateInfo.get("url");
 
        // String role = uidToRole.get(uid);
 
        // if (urlsToRoles.get(url).contains(role)) {
            return "success";
        // } else {
        //     throw new RuntimeException("没有权限");
        // }
    }
}
