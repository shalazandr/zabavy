package club.zabavy.core.service.impl;

import club.zabavy.core.dao.ParticipationDAO;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParticipationServiceImpl implements ParticipationService {

	@Autowired
	private ParticipationDAO participationDAO;

	@Override
	public List<User> getParticipants(long meetingId) {
		return participationDAO.getParticipants(meetingId);
	}

	@Override
	public List<Meeting> getMeetings(long userId) {
		return participationDAO.getMeetings(userId);
	}

	@Override
	public boolean existParticipation(long meetingId, long userId) {
		return participationDAO.existParticipation(meetingId, userId);
	}

	@Override
	public void addParticipation(long meetingId, long userId) {
		if(!existParticipation(meetingId, userId)) participationDAO.addParticipation(meetingId, userId);
		else throw new EntityExistsException("User already participate");
	}

	@Override
	public void removeParticipation(long meetingId, long userId) {
		if(existParticipation(meetingId, userId)) participationDAO.removeParticipation(meetingId, userId);
		else throw new EntityExistsException("User did not participate");
	}
}
