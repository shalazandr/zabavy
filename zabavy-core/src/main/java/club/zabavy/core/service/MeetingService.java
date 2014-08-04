package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Meeting;

import java.util.List;

public interface MeetingService extends BaseService<Meeting> {
	List<Meeting> getAll();
}