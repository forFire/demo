package com.flyer.service.impl;

import com.flyer.domain.Customer;
import com.flyer.domain.CustomerExample;
import com.flyer.mapper.CustomerMapper;
import com.flyer.service.CustomerService;
import com.flyer.util.PageInfo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerMapper customerMapper;

    public PageInfo<Customer> list() {
        PageInfo<Customer> pageInfo = new PageInfo<Customer>();
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();

        RowBounds rowBounds = new RowBounds((2-1) * 1,3);
        List<Customer> rows =  customerMapper.selectByExampleWithRowbounds(customerExample,rowBounds);
        Map<String, Object> result = new HashMap<String, Object>();
        int total = customerMapper.countByExample(customerExample);

        pageInfo.setCount(Long.valueOf(total));
        pageInfo.setRows(rows);
        return pageInfo;
    }

    public void add(Customer customer) {

    }
    
}
