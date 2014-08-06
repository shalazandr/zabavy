package club.zabavy.core.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Meeting {

	@Id
	@GeneratedValue
	private long id;
	private Status status;
	private Type type;
	private String title;
	private Date date;
	private String place;
	@ManyToOne(fetch = FetchType.EAGER)
	private User initiator;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public enum Status {
		PAST, PLANNED, WILLBE, CANCELED;
	}

	public enum Type{
		PUBLIC, PROTECTED, PRIVATE;
	}

	@Override
	public String toString() {
		return "Meeting{" +
				"id=" + id +
				", status=" + status +
				", type=" + type +
				", title='" + title + '\'' +
				", initiator=" + initiator +
				'}';
	}
}
