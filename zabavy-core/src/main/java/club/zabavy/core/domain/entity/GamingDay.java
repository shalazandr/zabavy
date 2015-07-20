package club.zabavy.core.domain.entity;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
@NamedQueries({
		@NamedQuery(
				name = "getEventsCount",
				query = "SELECT startTime AS date, COUNT(*) AS count FROM GamingDay e WHERE e.startTime BETWEEN :dateFrom AND :dateTo GROUP BY DATE(e.startTime)"
		)
})
public class GamingDay extends BaseEntity {

	private String title;
	@Column(length = 1000)
	private String description;
	private Date startTime;
	private Date endTime;

	public GamingDay() {}

	public GamingDay(String title, String description, Date startTime, Date endTime) {
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
