package com.vlasova.task4.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.vlasova.task4.entity.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
	private final SessionFactory sessionFactory;

	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public User findByUserName(String theUserName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			//do nothing
		}
		return theUser;
	}

	@Override
	public User getUser(int id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("from User order by userName", User.class);
		return query.getResultList();
	}

	@Override
	public void save(User theUser) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<User> query = session.createQuery("delete from User where id=:userId");
		query.setParameter("userId", id);
		query.executeUpdate();
	}
}