package club.zabavy.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Ownership {

	@Id
	@GeneratedValue
	private long id;
	private long gameboxId, userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGameboxId() {
		return gameboxId;
	}

	public void setGameboxId(long gameboxId) {
		this.gameboxId = gameboxId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
}
