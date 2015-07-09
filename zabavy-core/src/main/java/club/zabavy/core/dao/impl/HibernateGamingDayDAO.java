package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.GamingDayDAO;
import club.zabavy.core.domain.entity.GamingDay;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateGamingDayDAO implements GamingDayDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<GamingDay> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from GamingDay");
		return query.list();
	}

	@Override
	public GamingDay findById(long id) {
		return (GamingDay) sessionFactory.getCurrentSession().get(GamingDay.class, id);
	}

	@Override
	public void insert(GamingDay gamingDay) {
		sessionFactory.getCurrentSession().save(gamingDay);
	}

	@Override
	public GamingDay update(GamingDay gamingDay) {
		GamingDay d = findById(gamingDay.getId());

		if(gamingDay.getTitle() != null) d.setTitle(gamingDay.getTitle());
		if(gamingDay.getDescription() != null) d.setDescription(gamingDay.getDescription());
		if(gamingDay.getStartTime() != null) d.setStartTime(gamingDay.getStartTime());
		if(gamingDay.getEndTime() != null) d.setEndTime(gamingDay.getEndTime());

		d.setUpdatedAt(new Date());
		sessionFactory.getCurrentSession().update(d);
		return d;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		GamingDay gamingDay = findById(id);
		if(gamingDay != null) {
			session.delete(gamingDay);
		}
	}
}
