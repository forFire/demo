package com.flyer.controller;


import com.flyer.PageInfo;
import com.flyer.domain.Customer;
import com.flyer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "cumstomer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @ResponseBody
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Object list(PageInfo pageInfo){
        Map<String, Object> result = new HashMap<String, Object>();
        PageInfo<Customer> pageInfo1 = customerService.list(pageInfo);
        result.put("rows", pageInfo1.getRows());
        result.put("total", pageInfo1.getCount());
        return  result;
    }

    @ResponseBody
    @RequestMapping(value = "/saveorUpdate", method = RequestMethod.POST)
    public String saveorUpdate(Customer customer){
       Integer re =  customerService.saveorUpdate(customer);
        return "1";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer id){
          customerService.delete(id);
          return "success";
    }
}
