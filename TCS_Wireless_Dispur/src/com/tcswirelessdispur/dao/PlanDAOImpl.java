package com.tcswirelessdispur.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.tcswirelessdispur.model.Plan;

public class PlanDAOImpl implements PlanDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Plan p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> list() {
		Session session = this.sessionFactory.openSession();
		List<Plan> personList = session.createQuery("from Plan").list();
		session.close();
		return personList;
	}

	@Override
	public List<Plan> listOfSubscriptions(List<Integer> list) {
		try {
		Session session = this.sessionFactory.openSession();		
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("from Plan where planid in (:listofplans)").setParameterList("listofplans", list);
		List<Plan> personList = query.list();
		session.close();
		return personList;
		}
		catch(Exception e) {
			return new ArrayList<Plan>();
		}
	}
}
