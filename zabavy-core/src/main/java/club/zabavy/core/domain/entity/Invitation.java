package club.zabavy.core.domain.entity;

import javax.persistence.*;

@Entity
public class Invitation extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	private Meeting meeting;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	private Status status;
	private boolean used;

	public Invitation() {}

	public Invitation(Meeting meeting, User user) {
		this.meeting = meeting;
		this.user = user;
		this.status = Status.NEW;
	}

	public Invitation(Meeting meeting, User user, Status status, boolean used) {
		this.meeting = meeting;
		this.user = user;
		this.status = status;
		this.used = used;
	}

	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "Invitation: " + id + " " + status + ", " + used + ", meeting=" + meeting.getId() + ", user=" + user.getNickname();
	}

	public enum Status {
		NEW, REJECTED, ACCEPTED, CANCELED
	}
}
