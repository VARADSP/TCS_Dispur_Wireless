package com.tcswirelessdispur.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.tcswirelessdispur.model.Subscription;

public class SubscriptionDAOImpl implements SubscriptionDAO {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
	@Override
	public void save(Subscription p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(p);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscription> list() {
		Session session = this.sessionFactory.openSession();
		List<Subscription> personList = session.createQuery("from Subscription").list();
		session.close();
		return personList;
	}

}
