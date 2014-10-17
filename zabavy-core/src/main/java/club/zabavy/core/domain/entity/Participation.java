package club.zabavy.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Participation {

	@Id
	@GeneratedValue
	private long id;
	private long meetingId, userId;

	public Participation() {}

	public Participation(long meetingId, long userId) {
		this.meetingId = meetingId;
		this.userId = userId;
	}

	public long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
