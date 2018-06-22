package com.flyer.service;

import com.flyer.PageInfo;
import com.flyer.domain.Customer;

public interface CustomerService {

  PageInfo<Customer> list(PageInfo pageInfo);

  int saveorUpdate(Customer customer);

  void delete(Integer id);

}
