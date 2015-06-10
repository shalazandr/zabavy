package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
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
		return (Meeting) sessionFactory.getCurrentSession().get(Meeting.class, id);
	}

	@Override
	public void insert(Meeting meeting) {
		sessionFactory.getCurrentSession().save(meeting);
	}

	@Override
	public Meeting update(Meeting meeting) {
		Meeting m = findById(meeting.getId());

		if(meeting.getStatus() != null) m.setStatus(meeting.getStatus());
		if(meeting.getType() != null) m.setType(meeting.getType());
		if(meeting.getTitle() != null) m.setTitle(meeting.getTitle());
		if(meeting.getDate() != null) m.setDate(meeting.getDate());
		if(meeting.getPlace() != null) m.setPlace(meeting.getPlace());
		if(meeting.getInitiator() != null) m.setInitiator(meeting.getInitiator());

		sessionFactory.getCurrentSession().update(m);
		return m;
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Meeting meeting = findById(id);
		if(meeting != null) {
			session.delete(meeting);
		}
	}

	@Override
	public void changeInitiator(User from, User to) {
		if(from == null) {
			Query query = sessionFactory.getCurrentSession().createQuery("update Meeting set initiator = :newUser where initiator = null");
			query.setEntity("newUser", to);
			query.executeUpdate();
		} else if(to == null) {
			Query query = sessionFactory.getCurrentSession().createQuery("update Meeting set initiator = null where initiator = :oldUser");
			query.setEntity("oldUser", from);
			query.executeUpdate();
		} else {
			Query query = sessionFactory.getCurrentSession().createQuery("update Meeting set initiator = :newUser where initiator = :oldUser");
			query.setEntity("oldUser", from);
			query.setEntity("newUser", to);
			query.executeUpdate();
		}

	}
}
