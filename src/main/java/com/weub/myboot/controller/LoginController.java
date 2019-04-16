package com.weub.myboot.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        /*Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.se
        subject.login();*/

        String exception = (String) request.getAttribute("shiroLoginFailure");
        String message = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                message = "账号不存在";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                message = "密码错误";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                message = "验证码错误";
            } else {
                message = exception;
            }
        }
        map.put("msg", message);
        return "/login";
    }

    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping(value = "/403")
    public String unauthorizedRole() {
        return "/403";
    }

    /*public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123456";
        int hashIterations = 2;
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex(); //生成盐值
        System.out.println(salt);
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
    }*/
}
