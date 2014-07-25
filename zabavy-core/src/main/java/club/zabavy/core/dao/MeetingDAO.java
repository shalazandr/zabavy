package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Meeting;

import java.util.List;

public interface MeetingDAO {
	List<Meeting> getAll();
	Meeting findById(long id);
	void insert(Meeting meeting);
	void update(Meeting meeting);
	void remove(long id);
	void removeInitiatedBy(long userId);
}
