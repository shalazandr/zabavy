package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.SupplyDAO;
import club.zabavy.core.domain.entity.Supply;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class HibernateSupplyDAO implements SupplyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Supply findById(long id) {
		return (Supply) sessionFactory.getCurrentSession().get(Supply.class, id);
	}

	@Override
	public void insert(Supply entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(Supply entity) {
		Supply old = findById(entity.getId());
		if(old != null) {
			old.setStatus(entity.getStatus());
			sessionFactory.getCurrentSession().update(old);
		} else throw new NoSuchElementException("Supply with such id does not exist");
	}

	@Override
	public void remove(long id) {
		Supply old = findById(id);
		if(old != null) {
			sessionFactory.getCurrentSession().delete(old);
		} else throw new NoSuchElementException("Supply with such id does not exist");
	}

	@Override
	public List<Supply> findByParam(Long meetingId, Long userId, Long gameId, Supply.Status status) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Supply.class);
		if(meetingId != null) criteria.add(Restrictions.eq("meetingId", meetingId));
		if(userId != null) criteria.add(Restrictions.eq("userId", userId));
		if(gameId != null) criteria.add(Restrictions.eq("gameId", gameId));
		if(status != null) criteria.add(Restrictions.eq("status", status));
		return criteria.list();
	}
}
