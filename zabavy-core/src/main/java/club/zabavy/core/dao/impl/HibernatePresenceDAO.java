package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.PresenceDAO;
import club.zabavy.core.domain.entity.Presence;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernatePresenceDAO implements PresenceDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Presence findById(long id) {
		return (Presence) sessionFactory.getCurrentSession().get(Presence.class, id);
	}

	@Override
	public void insert(Presence entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Presence update(Presence entity) {
		Presence old = findById(entity.getId());

		if(entity.getTimeFrom() != null) old.setTimeFrom(entity.getTimeFrom());
		if(entity.getTimeTo() != null) old.setTimeTo(entity.getTimeTo());

		sessionFactory.getCurrentSession().update(old);
		return old;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Object entity = session.get(Presence.class, id);
		if(entity == null) throw new NoSuchElementException();
		else session.delete(entity);
	}

	@Override
	public List<Presence> findByParam(Long gamingDayId, Long userId, Boolean isEnded) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Presence.class);
		if(gamingDayId != null) criteria.add(Restrictions.eq("gamingDay.id", gamingDayId));
		if(userId != null) criteria.add(Restrictions.eq("user.id", userId));
		if(isEnded != null) {
			if(isEnded) {
				criteria.add(Restrictions.isNull("timeTo"));
			} else {
				criteria.add(Restrictions.isNotNull("timeTo"));
			}
		}
		return criteria.list();
	}
}
