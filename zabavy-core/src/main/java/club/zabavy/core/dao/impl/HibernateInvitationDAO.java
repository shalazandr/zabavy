package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.InvitationDAO;
import club.zabavy.core.domain.entity.Invitation;
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
public class HibernateInvitationDAO implements InvitationDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Invitation findById(long id) {
		return (Invitation) sessionFactory.getCurrentSession().get(Invitation.class, id);
	}

	@Override
	public void insert(Invitation entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public void update(Invitation entity) {
		Invitation invitation = findById(entity.getId());
		if(invitation != null) {
			invitation.setStatus(entity.getStatus());
			sessionFactory.getCurrentSession().update(invitation);
		}
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Object invitation = session.get(Invitation.class, id);
		if(invitation == null) throw new NoSuchElementException();
		else session.delete(invitation);
	}

	@Override
	public List<Invitation> findByParam(Long meetingId, Long userId, Invitation.Status status) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Invitation.class);
		if(meetingId != null) criteria.add(Restrictions.eq("meeting.id", meetingId));
		if(userId != null) criteria.add(Restrictions.eq("user.id", userId));
		if(status != null) criteria.add(Restrictions.eq("status", status));
		return criteria.list();
	}
}
