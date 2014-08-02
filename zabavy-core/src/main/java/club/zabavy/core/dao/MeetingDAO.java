package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Meeting;

import java.util.List;

public interface MeetingDAO extends BaseDAO<Meeting>{
	List<Meeting> getAll();
	void removeInitiatedBy(long userId);
}
