package com.flyer.controller;


import com.flyer.domain.User;
import com.flyer.service.LoginService;
import com.flyer.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user){
        boolean ret = loginService.login(user.getUserName(),user.getPassword());
        return "登陆成功";
    }

}


