package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.ScoreDAO;
import club.zabavy.core.domain.entity.Score;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class HibernateScoreDAO implements ScoreDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Score findById(long id) {
		return (Score) sessionFactory.getCurrentSession().get(Score.class, id);
	}

	@Override
	public void insert(Score entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Score update(Score entity) {
		sessionFactory.getCurrentSession().update(entity);
		entity.setUpdatedAt(new Date());
		return entity;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(Score.class, id));
	}

	@Override
	public List<Score> findByParam(Long matchId, Long userId, Boolean win) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Score.class);
		if(matchId != null) criteria.add(Restrictions.eq("match.id", matchId));
		if(userId != null) criteria.add(Restrictions.eq("user.id", userId));
		if(win != null) criteria.add(Restrictions.eq("tutorial", win));
		return criteria.list();
	}
}
