package com.tcswirelessdispur.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tcswirelessdispur.dao.CustomerDAO;
import com.tcswirelessdispur.dao.PlanDAO;
import com.tcswirelessdispur.dao.SubscriptionDAO;
import com.tcswirelessdispur.model.Customer;
import com.tcswirelessdispur.model.Plan;
import com.tcswirelessdispur.model.SubscribedPlans;
import com.tcswirelessdispur.model.Subscription;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;

 
@Controller
public class TCSWirelessDispur {
 
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {

		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("customer", "message", message);
	}
	
	@RequestMapping(value="/customer",method = RequestMethod.POST)
	public ModelAndView loginAsCustomer(@RequestParam("userid") String username,
			@RequestParam("password") String password,
			HttpSession session
			) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
		try {
			
		Customer customer = customerDAO.getCustomerById(Integer.parseInt(username));
		if(customer == null || !customer.getCustomerloginpassword().equals(password)) {
			context.close();
			return new ModelAndView("index","message","You have entered invalid credentials");
		}
		PlanDAO personDAO = context.getBean(PlanDAO.class);
		List<Plan> list = personDAO.list();
		for(Plan p : list){
			System.out.println("Plan List::"+p);
		}
		session.setAttribute("customerid", username);
		session.setAttribute("loginname", customer.getCustomername());
		//close resources
		context.close();
		session.setAttribute("planlist", list);
		return new ModelAndView("customer", "list", list);
		}
		catch (Exception e) {
			return new ModelAndView("index","message","You have entered invalid credentials");
		}
	}
	
	@RequestMapping(value="/manager",method = RequestMethod.POST)
	public ModelAndView loginAsManager(@RequestParam("userid") String username,
			@RequestParam("password") String password,
			HttpSession session) {
		if(username.equals("admin") && password.equals("admin")) {
		session.setAttribute("loginname", "admin");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		CustomerDAO personDAO = context.getBean(CustomerDAO.class);
		List<Customer> list = personDAO.list();
		HashMap<Customer,ArrayList<SubscribedPlans>> customerdata = new HashMap<Customer,ArrayList<SubscribedPlans>>();
		SubscriptionDAO subscriptionDAO = context.getBean(SubscriptionDAO.class);
		PlanDAO planDAO = context.getBean(PlanDAO.class);		
		for(Customer p : list){
			List<Subscription> listofsubscriptions = subscriptionDAO.list(p.getCustomerid());
			ArrayList<Integer> listOfPlanIds = new ArrayList<Integer>();
			for(Subscription s : listofsubscriptions) {
				listOfPlanIds.add(s.getSub_planid());
			}
			List<Plan> planList = planDAO.listOfSubscriptions(listOfPlanIds);
			ArrayList<SubscribedPlans> finalList = new ArrayList<SubscribedPlans>();
			for(int i=0;i<planList.size();i++) {
				finalList.add(new SubscribedPlans(planList.get(i).getPlanid(), planList.get(i).getPlanname(), planList.get(i).getPlantype(), planList.get(i).getPlantariff(), planList.get(i).getPlanvalidity(), planList.get(i).getPlanrental(), listofsubscriptions.get(i).getSub_startdate()));
			}
			customerdata.put(p,finalList);
		}
		//close resources
		context.close();
		session.setAttribute("customerdata", customerdata);
		return new ModelAndView("manager", "customerdata", customerdata);
		}
		else {
			return new ModelAndView("index","message","You have entered invalid credentials");
		}
		}
	
	@RequestMapping(value="/customer_subscriptions",method = RequestMethod.GET)
	public ModelAndView customerSubscriptions(
			HttpSession session
			) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		try {
		SubscriptionDAO personDAO = context.getBean(SubscriptionDAO.class);
		List<Subscription> list = personDAO.list(Integer.parseInt(session.getAttribute("customerid").toString()));
		ArrayList<Integer> listOfPlanIds = new ArrayList<Integer>();
		for(Subscription s : list) {
			listOfPlanIds.add(s.getSub_planid());
		}
		PlanDAO planDAO = context.getBean(PlanDAO.class);
		List<Plan> planList = planDAO.listOfSubscriptions(listOfPlanIds);
		List<SubscribedPlans> finalList = new ArrayList<SubscribedPlans>();
		for(int i=0;i<planList.size();i++) {
			finalList.add(new SubscribedPlans(planList.get(i).getPlanid(), planList.get(i).getPlanname(), planList.get(i).getPlantype(), planList.get(i).getPlantariff(), planList.get(i).getPlanvalidity(), planList.get(i).getPlanrental(), list.get(i).getSub_startdate()));
		}
		//close resources
		context.close();
		session.setAttribute("subscriptionlist", finalList);
		return new ModelAndView("customer_subscriptions", "list", finalList);
		}
		catch (Exception e) {
			return new ModelAndView("index","message","something went wrong ! Please try again");
		}
	}
	
	
	
	@RequestMapping(value="/subscribeplan",method = RequestMethod.GET)
	public ModelAndView subscribePlan(@RequestParam("planid") String planid,
			HttpSession session
			) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		try {
		SubscriptionDAO personDAO = context.getBean(SubscriptionDAO.class);
		Subscription newPlan = new Subscription();
		newPlan.setSub_planid(Integer.parseInt(planid));
		newPlan.setSub_customerid(Integer.parseInt(session.getAttribute("customerid").toString()));
		personDAO.save(newPlan);
		List<Subscription> list = personDAO.list(Integer.parseInt(session.getAttribute("customerid").toString()));
		ArrayList<Integer> listOfPlanIds = new ArrayList<Integer>();
		for(Subscription s : list) {
			listOfPlanIds.add(s.getSub_planid());
		}
		
		PlanDAO planDAO = context.getBean(PlanDAO.class);
		List<Plan> planList = planDAO.listOfSubscriptions(listOfPlanIds);
		List<SubscribedPlans> finalList = new ArrayList<SubscribedPlans>();
		for(int i=0;i<planList.size();i++) {
			finalList.add(new SubscribedPlans(planList.get(i).getPlanid(), planList.get(i).getPlanname(), planList.get(i).getPlantype(), planList.get(i).getPlantariff(), planList.get(i).getPlanvalidity(), planList.get(i).getPlanrental(), list.get(i).getSub_startdate()));
		}
		//close resources
		context.close();
		session.setAttribute("subscriptionlist", finalList);
		return new ModelAndView("customer_subscriptions", "list", finalList);
		}
		catch (Exception e) {
			return new ModelAndView("index","message","something went wrong ! Please try again");
		}
	}
	
	@RequestMapping(value="/cancelplan",method = RequestMethod.GET)
	public ModelAndView deletePlan(@RequestParam("planid") String planid,
			@RequestParam("planstartdate") String planstartdate,
			HttpSession session
			) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		try {
		SubscriptionDAO personDAO = context.getBean(SubscriptionDAO.class);
		Subscription newPlan = new Subscription();
		newPlan.setSub_planid(Integer.parseInt(planid));
		newPlan.setSub_customerid(Integer.parseInt(session.getAttribute("customerid").toString()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    java.util.Date startDate =  sdf.parse(planstartdate);
	    java.util.Date endDate=new java.util.Date();  	
	    int m1 = startDate.getYear() * 12 + startDate.getMonth();
	    int m2 = endDate.getYear() * 12 + endDate.getMonth();
	    int diff_in_months =  m2 - m1 + 1;
	    if(diff_in_months > 3 ) {
			personDAO.delete(newPlan);	
		}
	    else {
	    	model.put("error", "Plan must be 3 months old !");
	    }
		List<Subscription> list = personDAO.list(Integer.parseInt(session.getAttribute("customerid").toString()));
		ArrayList<Integer> listOfPlanIds = new ArrayList<Integer>();
		for(Subscription s : list) {
			listOfPlanIds.add(s.getSub_planid());
		}
		
		PlanDAO planDAO = context.getBean(PlanDAO.class);
		List<Plan> planList = planDAO.listOfSubscriptions(listOfPlanIds);
		List<SubscribedPlans> finalList = new ArrayList<SubscribedPlans>();
		for(int i=0;i<planList.size();i++) {
			finalList.add(new SubscribedPlans(planList.get(i).getPlanid(), planList.get(i).getPlanname(), planList.get(i).getPlantype(), planList.get(i).getPlantariff(), planList.get(i).getPlanvalidity(), planList.get(i).getPlanrental(), list.get(i).getSub_startdate()));
		}
		//close resources
		context.close();
		session.setAttribute("subscriptionlist", finalList);
		model.put("list", finalList);
		return new ModelAndView("customer_subscriptions", "model", model);
		}
		catch (Exception e) {
			System.out.println(e);
			return new ModelAndView("index","message","something went wrong ! Please try again");
		}
	}
	
	
	@RequestMapping(value="**",method = RequestMethod.GET)
	public String getAnythingelse(HttpSession session){
	if(session.getAttribute("loginname") != null && session.getAttribute("loginname").equals("admin")) {
		return "manager";
	}
	else if(session.getAttribute("customerid") != null) {
		return "customer";
	}
	else {
		session.invalidate();
		return "index";
	}
	}
	
	@RequestMapping("/logout")  
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("customerid");
		session.removeAttribute("loginname");
		 session.invalidate();
		return new ModelAndView("index");
	}
}