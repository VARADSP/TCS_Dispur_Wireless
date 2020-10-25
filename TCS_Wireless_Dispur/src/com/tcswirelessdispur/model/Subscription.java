package com.tcswirelessdispur.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * 
 *
 */
@Entity
@Table(name="Subscription")
public class Subscription {

	@Id
	@Column(name="subscriptionid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int subscriptionid;
	
	private int sub_customerid;
	
	private int sub_planid;
	
	private String sub_startdate;

	public int getSubscriptionid() {
		return subscriptionid;
	}

	public void setSubscriptionid(int subscriptionid) {
		this.subscriptionid = subscriptionid;
	}

	public int getSub_customerid() {
		return sub_customerid;
	}

	public void setSub_customerid(int sub_customerid) {
		this.sub_customerid = sub_customerid;
	}

	public int getSub_planid() {
		return sub_planid;
	}

	public void setSub_planid(int sub_planid) {
		this.sub_planid = sub_planid;
	}

	public String getSub_startdate() {
		return sub_startdate;
	}

	public void setSub_startdate(String sub_startdate) {
		this.sub_startdate = sub_startdate;
	}

	@Override
	public String toString() {
		return "Subscription [subscriptionid=" + subscriptionid + ", sub_customerid=" + sub_customerid + ", sub_planid="
				+ sub_planid + ", sub_startdate=" + sub_startdate + "]";
	}

		
	
}