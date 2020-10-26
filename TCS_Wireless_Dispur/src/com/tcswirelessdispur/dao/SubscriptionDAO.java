package com.tcswirelessdispur.dao;

import java.util.List;

import com.tcswirelessdispur.model.Plan;
import com.tcswirelessdispur.model.Subscription;

public interface SubscriptionDAO {

	public void save(Subscription p);
	
	public void delete(Subscription p);
	
	public List<Subscription> list(int customerid);
	
}