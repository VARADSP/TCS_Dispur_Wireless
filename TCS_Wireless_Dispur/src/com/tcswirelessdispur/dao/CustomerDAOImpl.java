package com.tcswirelessdispur.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.tcswirelessdispur.model.Customer;
import com.tcswirelessdispur.model.Plan;

public class CustomerDAOImpl implements CustomerDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Customer p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> list() {
		Session session = this.sessionFactory.openSession();
		List<Customer> personList = session.createQuery("from Customer").list();
		session.close();
		return personList;
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		Session session = this.sessionFactory.getCurrentSession();	
		Transaction tx = session.beginTransaction();
		Customer p = (Customer) session.get(Customer.class, new Integer(id));
		tx.commit();
		System.out.println(p);
		return p;
	}
}
