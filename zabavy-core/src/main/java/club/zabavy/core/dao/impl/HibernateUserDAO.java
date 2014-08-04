package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.dao.OwnershipDAO;
import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateUserDAO implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	OwnershipDAO ownershipDAO;

	@Autowired
	MeetingDAO meetingDAO;

	@Override
	public List<User> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.list();
	}

	@Override
	public User findById(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where id = :id");
		query.setLong("id", id);
		return (User) query.uniqueResult();
	}

	@Override
	public void insert(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void update(User user) {
		User u = findById(user.getId());

		if(user.getNickname() != null) u.setNickname(user.getNickname());
		if(user.getFirstName() != null) u.setFirstName(user.getFirstName());
		if(user.getLastName() != null) u.setLastName(user.getLastName());
		if(user.getPhotoUrl() != null) u.setPhotoUrl(user.getPhotoUrl());
		if(user.getLevel() > 0) u.setLevel(user.getLevel());
		if(user.getRole() != null) u.setRole(user.getRole());

		sessionFactory.getCurrentSession().update(u);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, id);
		if(user != null) {
			meetingDAO.changeInitiator(user, null);
			ownershipDAO.deleteOwnershipsForUser(id);
			session.delete(user);
		}
	}
}
