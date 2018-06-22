package com.flyer.service.impl;

import com.flyer.Page;
import com.flyer.PageInfo;
import com.flyer.domain.Customer;
import com.flyer.domain.CustomerExample;
import com.flyer.mapper.CustomerMapper;
import com.flyer.service.CustomerService;
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

    public PageInfo<Customer> list(PageInfo pageInfo) {
        PageInfo<Customer> pageInfo1 = new PageInfo<Customer>();
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();

        RowBounds rowBounds = new RowBounds((pageInfo.getPageNumber()-1)*pageInfo.getPageNumber(),pageInfo.getPageSize());
        List<Customer> rows =  customerMapper.selectByExampleWithRowbounds(customerExample,rowBounds);
        Map<String, Object> result = new HashMap<String, Object>();
        int total = customerMapper.countByExample(customerExample);

        pageInfo1.setCount(Long.valueOf(total));
        pageInfo1.setRows(rows);
        return pageInfo1;
    }

    public int saveorUpdate(Customer customer) {
        Customer c =    customerMapper.selectByPrimaryKey(customer.getId());
        if(c!= null){
            customerMapper.updateByPrimaryKey(customer);
        }else{
            customerMapper.insert(customer);
        }
      return   0;

    }

    public void delete(Integer id) {
        customerMapper.deleteByPrimaryKey(id);
    }
}
