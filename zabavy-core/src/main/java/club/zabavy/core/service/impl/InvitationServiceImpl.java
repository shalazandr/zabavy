package club.zabavy.core.service.impl;

import club.zabavy.core.dao.InvitationDAO;
import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.entity.Invitation;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationDAO invitationDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MeetingDAO meetingDAO;

	@Override
	public Invitation findById(long id) {
		return invitationDAO.findById(id);
	}

	@Override
	public void insert(Invitation entity) {
		List<Invitation> lol = findByParam(entity.getMeeting().getId(), entity.getUser().getId(), null, null);
		if(lol.size() > 0) throw new EntityExistsException("User already invited to this meeting");

		Meeting meeting = meetingDAO.findById(entity.getMeeting().getId());
		User user = userDAO.findById(entity.getUser().getId());
		if(meeting == null) throw new NullPointerException("Meeting should be specified!");
		if (user != null) throw new NullPointerException("User should be specified!");

		if(entity.getStatus() == null) entity.setStatus(Invitation.Status.NEW);
		invitationDAO.insert(entity);
	}

	@Override
	public Invitation update(Invitation entity) {
		return invitationDAO.update(entity);
	}

	@Override
	public void remove(long id) {
		invitationDAO.remove(id);
	}

	@Override
	public List<Invitation> findByParam(Long meetingId, Long userId, Invitation.Status status, Boolean used) {
		return invitationDAO.findByParam(meetingId, userId, status, used);
	}
}
