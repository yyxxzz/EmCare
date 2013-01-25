package com.company.emcare.interceptor;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

public class HibernateFilter extends OpenSessionInViewFilter {

//	@Override
//	protected Session getSession(SessionFactory sessionFactory)
//			throws DataAccessResourceFailureException {
//		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
//		// set the FlushMode to auto in order to save objects.
//		session.setFlushMode(FlushMode.AUTO);
//		return session;
//	}
	
}