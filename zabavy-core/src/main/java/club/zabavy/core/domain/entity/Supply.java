package club.zabavy.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Supply {

	@Id
	@GeneratedValue
	private long id;
	private long userId, meetingId, gameId;
	private Status status;


	enum Status {
		NO, ON_DEMAND, YES
	}
}
