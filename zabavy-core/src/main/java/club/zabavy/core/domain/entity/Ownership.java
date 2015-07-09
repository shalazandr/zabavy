package club.zabavy.core.domain.entity;

import javax.persistence.Entity;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Ownership extends BaseEntity {

	private long gameboxId, userId;

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
