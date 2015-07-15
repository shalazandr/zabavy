package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.MatchDAO;
import club.zabavy.core.domain.entity.Match;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class HibernateMatchDAO implements MatchDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Match findById(long id) {
		return (Match) sessionFactory.getCurrentSession().get(Match.class, id);
	}

	@Override
	public void insert(Match entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Match update(Match entity) {
		Match old = findById(entity.getId());
		if (old == null) {
			throw new NoSuchElementException();
		}
		if (entity.isTutorial() != null) old.setTutorial(entity.isTutorial());
		if (entity.getWinType() != null) old.setWinType(entity.getWinType());
		if (entity.getEvent() != null) old.setEvent(entity.getEvent());
		if (entity.getMainGamebox() != null) old.setMainGamebox(entity.getMainGamebox());
		if (entity.getAdditionalGameboxes() != null) old.setAdditionalGameboxes(entity.getAdditionalGameboxes());
		old.setUpdatedAt(new Date());
		sessionFactory.getCurrentSession().update(old);
		return old;
	}

	@Override
	public void remove(long id) {
		Match old = findById(id);
		if (old == null) {
			throw new NoSuchElementException();
		}
		sessionFactory.getCurrentSession().delete(old);
	}

	@Override
	public List<Match> findByParam(Long eventId, Long gameboxId, Boolean isTutorial, int offset, int limit) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Match.class);
		if(eventId != null) criteria.add(Restrictions.eq("event.id", eventId));
		if(gameboxId != null) criteria.add(Restrictions.eq("gamebox.id", gameboxId));
		if(isTutorial != null) criteria.add(Restrictions.eq("tutorial", isTutorial));
		criteria.setFirstResult(offset);
		criteria.setMaxResults(limit);
		return criteria.list();
	}
}
