package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.domain.entity.Meeting;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateMeetingDAO implements MeetingDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Meeting> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Meeting");
		return query.list();
	}

	@Override
	public Meeting findById(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Meeting where id = :id");
		query.setLong("id", id);
		return (Meeting) query.uniqueResult();
	}

	@Override
	public void insert(Meeting meeting) {
		sessionFactory.getCurrentSession().save(meeting);
	}

	@Override
	public void update(Meeting meeting) {
		Meeting m = findById(meeting.getId());

		if(meeting.getStatus() != null) m.setStatus(meeting.getStatus());
		if(meeting.getType() != null) m.setType(meeting.getType());
		if(meeting.getTitle() != null) m.setTitle(meeting.getTitle());
		if(meeting.getDate() != null) m.setDate(meeting.getDate());
		if(meeting.getPlace() != null) m.setPlace(meeting.getPlace());
		if(meeting.getInitiator() != null) m.setInitiator(meeting.getInitiator());

		sessionFactory.getCurrentSession().update(m);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Meeting meeting = (Meeting) session.load(Meeting.class, id);
		if(meeting != null) {
			session.delete(meeting);
		}
	}

	@Override
	public void removeInitiatedBy(long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Meeting where initiator = :initiator");
		query.setLong("initiator", userId);
		query.executeUpdate();
	}
}
