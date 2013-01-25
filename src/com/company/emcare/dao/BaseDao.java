package com.company.emcare.dao;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseDao extends HibernateDaoSupport {
    protected Log log = LogFactory.getLog(getClass());
    
    @Resource
    public void initSessionFactory(SessionFactory sessionFactory){
    	super.setSessionFactory(sessionFactory);
    }
    
    
    
}
