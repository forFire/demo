package com.flyer.controller;


import com.flyer.PageInfo;
import com.flyer.domain.Customer;
import com.flyer.domain.User;
import com.flyer.service.CustomerService;
import com.flyer.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @ResponseBody
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveorUpdate(User user){
       int ret = registerService.register(user);
        return "注册成功";
    }

}


