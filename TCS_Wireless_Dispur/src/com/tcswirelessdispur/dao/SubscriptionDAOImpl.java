package com.tcswirelessdispur.dao;

import java.util.List;

import org.hibernate.Query;
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
	
	@Override
	public void delete(Subscription p) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(p);
		Query query = session.createQuery("Delete from Subscription r where r.sub_customerid = :x and r.sub_planid = :y");
		query.setParameter("x", p.getSub_customerid());
		query.setParameter("y", p.getSub_planid());		
		query.executeUpdate();
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscription> list(int customerid) {
		Session session = this.sessionFactory.openSession();
		List<Subscription> personList = session.createQuery("from Subscription where sub_customerid="+customerid).list();
		session.close();
		return personList;
	}

}
