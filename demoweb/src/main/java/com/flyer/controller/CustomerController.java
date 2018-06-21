package com.flyer.controller;


import com.flyer.domain.Customer;
import com.flyer.service.CustomerService;
import com.flyer.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "cumstomer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @ResponseBody
    @RequestMapping("/findList")
    public Object list(){
        Map<String, Object> result = new HashMap<String, Object>();
        PageInfo<Customer> pageInfo = customerService.list();
        result.put("Rows", pageInfo.getRows());
        result.put("Total", pageInfo.getCount());
        return  result;
    }



}
