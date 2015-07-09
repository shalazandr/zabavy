package club.zabavy.core.domain.entity;

import javax.persistence.Entity;

@Entity
public class Supply extends BaseEntity {

	private long userId, meetingId, gameId;
	private Status status;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(long meetingId) {
		this.meetingId = meetingId;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		NO, ON_DEMAND, YES
	}
}
