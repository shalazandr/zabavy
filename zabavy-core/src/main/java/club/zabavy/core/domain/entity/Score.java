package club.zabavy.core.domain.entity;

import javax.persistence.*;

@Entity
public class Score {
	@Id
	@GeneratedValue
	private long id;
	private boolean win;
	private short points;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	@ManyToOne(fetch = FetchType.EAGER)
	private Match match;

	public Score() {}

	public Score(boolean win, short points, User user, Match match) {
		this.win = win;
		this.points = points;
		this.user = user;
		this.match = match;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public short getPoints() {
		return points;
	}

	public void setPoints(short points) {
		this.points = points;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public String toString() {
		return "Score{" +
				"id=" + id +
				", win=" + win +
				", points=" + points +
				", user=" + user.getNickname() + " (id: " + user.getId() + ")" +
				", match=" + match +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Score score = (Score) o;

		if (id != score.id) return false;
		if (win != score.win) return false;
		if (points != score.points) return false;
		if (user != null ? !user.equals(score.user) : score.user != null) return false;
		return !(match != null ? !match.equals(score.match) : score.match != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (win ? 1 : 0);
		result = 31 * result + (int) points;
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (match != null ? match.hashCode() : 0);
		return result;
	}
}
