package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface ParticipationService {
	List<User> getParticipants(long meetingId);
	List<Meeting> getMeetings(long userId);
	boolean existParticipation(long meetingId, long userId);
	void addParticipation(long meetingId, long userId);
	void removeParticipation(long meetingId, long userId);
}
