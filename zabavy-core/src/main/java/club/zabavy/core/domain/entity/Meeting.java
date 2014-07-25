package club.zabavy.core.domain.entity;

import club.zabavy.core.domain.MeetingStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Meeting {

	@Id
	@GeneratedValue
	private long id;
	private MeetingStatus status;
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

	public MeetingStatus getStatus() {
		return status;
	}

	public void setStatus(MeetingStatus status) {
		this.status = status;
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
}
