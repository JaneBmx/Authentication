package com.vlasova.task4.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.vlasova.task4.entity.Authorities;

@Repository
public class AuthoritiesDaoImpl implements AuthoritiesDao {
	private final SessionFactory sessionFactory;

	public AuthoritiesDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Authorities findRoleByName(String theRoleName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Authorities> theQuery = currentSession.createQuery("from Authorities where authority=:roleName", Authorities.class);
		theQuery.setParameter("roleName", theRoleName);
		Authorities theRole;
		try {
			theRole = theQuery.getSingleResult();
		} catch (Exception e) {
			theRole = null;
		}
		return theRole;
	}
}