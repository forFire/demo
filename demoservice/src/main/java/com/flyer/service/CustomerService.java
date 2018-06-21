package com.flyer.service;

import com.flyer.domain.Customer;
import com.flyer.util.PageInfo;

public interface CustomerService {

  PageInfo<Customer> list();

  void add(Customer customer);

}
