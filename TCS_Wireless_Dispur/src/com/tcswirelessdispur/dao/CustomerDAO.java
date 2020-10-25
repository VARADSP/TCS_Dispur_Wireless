package com.tcswirelessdispur.dao;

import java.util.List;

import com.tcswirelessdispur.model.Customer;
import com.tcswirelessdispur.model.Plan;
import com.tcswirelessdispur.model.Subscription;

public interface CustomerDAO {

	public void save(Customer p);
	
	public Customer getCustomerById(int id);
	
	public List<Customer> list();
	
}