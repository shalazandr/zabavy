package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface MeetingDAO extends BaseDAO<Meeting>{
	List<Meeting> getAll();
	void changeInitiator(User from, User to);
}
