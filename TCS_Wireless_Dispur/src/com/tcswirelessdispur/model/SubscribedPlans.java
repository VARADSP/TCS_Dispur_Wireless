package com.tcswirelessdispur.model;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * 
 *
 */
public class SubscribedPlans {

	private int planid;
	
	private String planname;
	
	private String plantype;
	
	private String plantariff;
	
	private String planvalidity;
	
	private String planrental;
	
	private String planstartdate;
	
	
	public String getPlanstartdate() {
		return planstartdate;
	}

	public void setPlanstartdate(String planstartdate) {
		this.planstartdate = planstartdate;
	}

	public String getPlanrental() {
		return planrental;
	}

	public void setPlanrental(String planrental) {
		this.planrental = planrental;
	}

	public int getPlanid() {
		return planid;
	}

	public void setPlanid(int planid) {
		this.planid = planid;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public String getPlantype() {
		return plantype;
	}

	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}

	public String getPlantariff() {
		return plantariff;
	}

	public void setPlantariff(String plantariff) {
		this.plantariff = plantariff;
	}

	public String getPlanvalidity() {
		return planvalidity;
	}

	public void setPlanvalidity(String planvalidity) {
		this.planvalidity = planvalidity;
	}



	@Override
	public String toString() {
		return "SubscribedPlans [planid=" + planid + ", planname=" + planname + ", plantype=" + plantype
				+ ", plantariff=" + plantariff + ", planvalidity=" + planvalidity + ", planrental=" + planrental
				+ ", planstartdate=" + planstartdate + "]";
	}

	public SubscribedPlans(int planid, String planname, String plantype, String plantariff, String planvalidity,
			String planrental, String planstartdate) {
		super();
		this.planid = planid;
		this.planname = planname;
		this.plantype = plantype;
		this.plantariff = plantariff;
		this.planvalidity = planvalidity;
		this.planrental = planrental;
		this.planstartdate = planstartdate;
	}
	
}