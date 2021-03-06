package com.tcswirelessdispur.dao;

import java.util.List;

import com.tcswirelessdispur.model.Plan;

public interface PlanDAO {

	public void save(Plan p);
	
	public List<Plan> list();
	
	public List<Plan> listOfSubscriptions(List<Integer> list);

	public List<Plan> listOfRemainingPlans(List<Integer> list);
}