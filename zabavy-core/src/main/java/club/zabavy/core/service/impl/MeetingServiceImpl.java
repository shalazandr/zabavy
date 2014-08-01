package club.zabavy.core.service.impl;

import club.zabavy.core.dao.MeetingDAO;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	MeetingDAO meetingDAO;

	@Override
	public List<Meeting> getAll() {
		return meetingDAO.getAll();
	}

	@Override
	public Meeting findById(long id) {
		return meetingDAO.findById(id);
	}

	@Override
	public void insert(Meeting meeting) {
		if(meeting.getInitiator() == null) throw new NullPointerException("Initiator cant be null.");
		meetingDAO.insert(meeting);
	}

	@Override
	public void update(Meeting meeting) {
		if(meeting.getId() <= 0) throw new NullPointerException("Wrong id.");
		meetingDAO.update(meeting);
	}

	@Override
	public void remove(long id) {
		if(id <= 0) throw new NullPointerException("Wrong id.");
		meetingDAO.remove(id);
	}

	@Override
	public void removeInitiatedBy(long userId) {
		meetingDAO.removeInitiatedBy(userId);
	}
}