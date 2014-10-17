package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.ParticipationDAO;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.Participation;
import club.zabavy.core.domain.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateParticipationDAO implements ParticipationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<User> getParticipants(long meetingId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where id in (select userId from Participation where meetingId = :mid)");
		query.setLong("mid", meetingId);
		return query.list();
	}

	@Override
	public List<Meeting> getMeetings(long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Meeting where id in (select meetingId from Participation where userId = :uid)");
		query.setLong("uid", userId);
		return query.list();
	}

	@Override
	public boolean existParticipation(long meetingId, long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Participation where meetingId = :mid and userId = :uid");
		query.setLong("mid", meetingId);
		query.setLong("uid", userId);
		return query.uniqueResult() != null;
	}

	@Override
	public void addParticipation(long meetingId, long userId) {
		Participation participation = new Participation(meetingId, userId);
		sessionFactory.getCurrentSession().save(participation);
	}

	@Override
	public void removeParticipation(long meetingId, long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Participation where meetingId = :mid and userId = :uid");
		query.setLong("mid", meetingId);
		query.setLong("uid", userId);
		query.executeUpdate();
	}
}
