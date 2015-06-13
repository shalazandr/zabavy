package club.zabavy.core.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Presence {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	private GamingDay gamingDay;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	private Date timeFrom;
	private Date timeTo;

	public Presence() {}

	public Presence(GamingDay gamingDay, User user, Date timeFrom) {
		this.gamingDay = gamingDay;
		this.user = user;
		this.timeFrom = timeFrom;
	}

	public Presence(GamingDay gamingDay, User user, Date timeFrom, Date timeTo) {
		this.gamingDay = gamingDay;
		this.user = user;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GamingDay getGamingDay() {
		return gamingDay;
	}

	public void setGamingDay(GamingDay gamingDay) {
		this.gamingDay = gamingDay;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	@Override
	public String toString() {
		return "Presence: " + id + " " + timeFrom + ", " + timeTo + ", gamingDay=" + gamingDay.getId() + ", user=" + user.getNickname();
	}
}
