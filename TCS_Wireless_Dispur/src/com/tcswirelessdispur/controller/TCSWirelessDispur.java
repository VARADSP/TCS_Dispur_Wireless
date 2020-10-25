package com.tcswirelessdispur.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tcswirelessdispur.dao.CustomerDAO;
import com.tcswirelessdispur.dao.PlanDAO;
import com.tcswirelessdispur.model.Customer;
import com.tcswirelessdispur.model.Plan;

import java.util.List;

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
		//close resources
		context.close();
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
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");	
		CustomerDAO personDAO = context.getBean(CustomerDAO.class);
		List<Customer> list = personDAO.list();
		for(Customer p : list){
			System.out.println("Customer List::"+p);
		}
		//close resources
		context.close();
		return new ModelAndView("manager", "list", list);
		}
		else {
			return new ModelAndView("index","message","You have entered invalid credentials");
		}
		}
	
	
	
	
	@RequestMapping("/logout")  
	public ModelAndView logout() {
		 
		return new ModelAndView("index");
	}
}