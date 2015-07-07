package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.dao.OwnershipDAO;
import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
	public List<User> findByParam(String name, Integer level, Role role) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);

		if(name != null) criteria.add( Restrictions.or(
				Restrictions.ilike("nickname", name, MatchMode.ANYWHERE),
				Restrictions.ilike("firstName", name, MatchMode.ANYWHERE),
				Restrictions.ilike("lastName", name, MatchMode.ANYWHERE)
		));
		if(level != null) {
			criteria.add(Restrictions.eq("lvl", level));
		}
		if(role != null) {
			criteria.add(Restrictions.eq("role", role));
		}

		return criteria.list();
	}

	@Override
	public User findById(long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void insert(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User update(User user) {
		User u = findById(user.getId());

		if(user.getNickname() != null) u.setNickname(user.getNickname());
		if(user.getFirstName() != null) u.setFirstName(user.getFirstName());
		if(user.getLastName() != null) u.setLastName(user.getLastName());
		if(user.getPhotoUrl() != null) u.setPhotoUrl(user.getPhotoUrl());
		if(user.getLevel() > 0) u.setLevel(user.getLevel());
		if(user.getRole() != null) u.setRole(user.getRole());

		sessionFactory.getCurrentSession().update(u);
		return u;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findById(id);
		if(user != null) {
			meetingDAO.changeInitiator(user, null);
			ownershipDAO.deleteOwnershipsForUser(id);
			session.delete(user);
		}
	}
}
