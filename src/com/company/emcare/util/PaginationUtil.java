package com.company.emcare.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.Projections;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
@Service("paginationUtil")
public class PaginationUtil implements ApplicationContextAware{
	private static ApplicationContext context;
	public static int getRecordsSize(Class clazz){
		SessionFactory sessionFactory = context.getBean("sessionFactory",SessionFactory.class);
		StatelessSession session = sessionFactory.openStatelessSession();
		long result =  (Long)session.createCriteria(clazz).setProjection(Projections.rowCount()).list().get(0);
		session.close();
		return (int)result;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
		 
	}
}
