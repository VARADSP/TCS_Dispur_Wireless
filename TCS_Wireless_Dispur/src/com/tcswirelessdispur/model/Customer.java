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
@Table(name="Customer")
public class Customer {

	@Id
	@Column(name="customerid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerid;
	
	private String customername;
	
	private String customeraddress;
	
	private String customeremail;
	
	private int customercontactnumber;
	
	private String customerloginpassword;

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomeremail() {
		return customeremail;
	}

	public void setCustomeremail(String customeremail) {
		this.customeremail = customeremail;
	}

	public int getCustomercontactnumber() {
		return customercontactnumber;
	}

	public void setCustomercontactnumber(int customercontactnumber) {
		this.customercontactnumber = customercontactnumber;
	}

	public String getCustomerloginpassword() {
		return customerloginpassword;
	}

	public void setCustomerloginpassword(String customerloginpassword) {
		this.customerloginpassword = customerloginpassword;
	}

	@Override
	public String toString() {
		return "Customer [customerid=" + customerid + ", customername=" + customername + ", customeraddress="
				+ customeraddress + ", customeremail=" + customeremail + ", customercontactnumber="
				+ customercontactnumber + ", customerloginpassword=" + customerloginpassword + "]";
	}
	
		
	
}