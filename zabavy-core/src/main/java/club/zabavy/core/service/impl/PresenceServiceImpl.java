package club.zabavy.core.service.impl;

import club.zabavy.core.dao.*;
import club.zabavy.core.domain.entity.*;
import club.zabavy.core.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PresenceServiceImpl implements PresenceService {

	@Autowired
	private PresenceDAO presenceDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private GamingDayDAO gamingDayDAO;

	@Override
	public Presence findById(long id) {
		return presenceDAO.findById(id);
	}

	@Override
	public void insert(Presence entity) {
		List<Presence> lol = findByParam(entity.getGamingDay().getId(), entity.getUser().getId(), null);
		if(lol.size() > 0) throw new EntityExistsException("User already visited this day");

		GamingDay gamingDay = gamingDayDAO.findById(entity.getGamingDay().getId());
		User user = userDAO.findById(entity.getUser().getId());
		if(gamingDay == null) throw new NullPointerException("There isn't such day");
		if(user == null) throw new NullPointerException("There isn't such user");

		if(entity.getTimeFrom() == null) entity.setTimeFrom(new Date());
		presenceDAO.insert(entity);
	}

	@Override
	public Presence update(Presence entity) {
		return presenceDAO.update(entity);
	}

	@Override
	public void remove(long id) {
		presenceDAO.remove(id);
	}

	@Override
	public List<Presence> findByParam(Long gamingDayId, Long userId, Boolean isEnded) {
		return presenceDAO.findByParam(gamingDayId, userId, isEnded);
	}
}
