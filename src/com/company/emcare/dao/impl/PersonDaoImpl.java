package com.company.emcare.dao.impl;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.company.emcare.dao.BaseDao;
import com.company.emcare.dao.PersonDao;
import com.company.emcare.model.Person;
import com.company.emcare.model.Role;
@Transactional
@Repository(value="personDao")
public class PersonDaoImpl extends BaseDao implements PersonDao {

	@Override
	public Person login(String username, String password) {
		// TODO Auto-generated method stub
		Person person = null;
		DetachedCriteria criteria = DetachedCriteria.forClass(Person.class)
		.add( Restrictions.eq("username", username))
		.add (Restrictions.eq("password", password));
		
		List result = this.getHibernateTemplate().findByCriteria(criteria);
		if(result.size() != 0) {
			person = (Person) result.get(0);
		}
		return person;
	}

	@Override
	public List<Person> getAllUser(int firstResult,	int maxResult,String query) {
		
		String hql = "";		
		if(query == null || query.trim().equals("")){
			hql = "FROM Person p order by p.username asc";
		}else{
			hql = "FROM Person p WHERE p.realname like '%"+ query +"%' order by p.username asc";
		}
		
		final String to = hql;
		final int fr = firstResult;
		final int mr = maxResult;
		
		List<Person> result = this.getHibernateTemplate().execute(new HibernateCallback<List<Person>>() {

			@Override
			public List<Person> doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query = session.createQuery(to);
				query.setFirstResult(fr);
				query.setMaxResults(mr);
				return query.list();
			}
		});
		return result;
		
	}

	@Override
	public void addUser(Person person) {
		// TODO Auto-generated method stub
		getHibernateTemplate().save(person);
		
	}

	@Override
	public void updateUser(Person person) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(person);
		
	}

	@Override
	public void deleteUser(Person person) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(person);
		
	}

	@Override
	public Person getUser(Long id) {
		// TODO Auto-generated method stub
//		return getHibernateTemplate().load(Person.class, id);
		return (Person) getHibernateTemplate().find("from Person where personId="+id).get(0);
	}

	@Override
	public Set<Person> getPersonByRoleType(int roleType) {
//		Criteria criteria = this.getSession().createCriteria(Role.class);
//		criteria.add(Restrictions.eq("roleType", roleType));
		getHibernateTemplate().find("from Role where roleType="+roleType);
		List<Role> roles = getHibernateTemplate().find("from Role where roleType="+roleType);
		Set<Person> persons = new HashSet<Person>();
		for(Role role:roles) {
			persons.addAll(role.getPersonMembers());
		}
		return persons;
	}
	
	@Override
	public Role getRoleByRoleType(int roleType){
		List<Role> roles = getHibernateTemplate().find("from Role where roleType="+roleType);
		return roles.get(0);
	}


	@Override
	public Person getPersonByUserName(String username) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Person.class);
		criteria.add(Restrictions.eq("username", username));
		List<Person> result = getHibernateTemplate().findByCriteria(criteria);
		if(!result.isEmpty())
			return result.get(0);
		else
			return null;
	}

	@Override
	public int getTotalAcount(String query) {
		Criteria criteria = getSession().createCriteria(Person.class).setProjection(Projections.rowCount());
		if(!StringUtils.isEmpty(query))
			criteria.add(Restrictions.like("realname", query));
		List result = criteria.list();
		if(!result.isEmpty())
			return	((Long) result.get(0)).intValue();
		else
			return 0;
	}
}
