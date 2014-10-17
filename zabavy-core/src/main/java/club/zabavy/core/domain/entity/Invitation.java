package club.zabavy.core.domain.entity;

import javax.persistence.*;

@Entity
public class Invitation {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private Meeting meeting;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	private Status status;

	public Invitation() {}

	public Invitation(Meeting meeting, User user) {
		this.meeting = meeting;
		this.user = user;
		this.status = Status.NEW;
	}

	public Invitation(Meeting meeting, User user, Status status) {
		this.meeting = meeting;
		this.user = user;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Invitation: " +
				"  id=" + id +
				", status=" + status + ",\n" +
				"    meeting=" + meeting + ",\n" +
				"    user=" + user;
	}

	public enum Status {
		NEW, REJECTED, ACCEPTED, CANCELED
	}
}
