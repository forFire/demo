package com.flyer;

import java.beans.Customizer;

public interface CustomerService {

 //    public

  PageInfo<Customer> list();

  void add(Customer customer);

}
