package club.zabavy.core.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Maatch")
public class Match extends BaseEntity {

	private Boolean tutorial;
	private WinType winType;
	@ManyToOne(fetch = FetchType.EAGER)
	private GamingDay event;
	@ManyToOne(fetch = FetchType.EAGER)
	private Gamebox mainGamebox;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Gamebox> additionalGameboxes;

	public Match() {}

	public Match(Boolean tutorial, WinType winType, GamingDay event, Gamebox mainGamebox, Set<Gamebox> additionalGameboxes) {
		this.tutorial = tutorial;
		this.winType = winType;
		this.event = event;
		this.mainGamebox = mainGamebox;
		this.additionalGameboxes = additionalGameboxes;
	}

	public Boolean isTutorial() {
		return tutorial;
	}

	public void setTutorial(Boolean tutorial) {
		this.tutorial = tutorial;
	}

	public WinType getWinType() {
		return winType;
	}

	public void setWinType(WinType winType) {
		this.winType = winType;
	}

	public GamingDay getEvent() {
		return event;
	}

	public void setEvent(GamingDay event) {
		this.event = event;
	}

	public Gamebox getMainGamebox() {
		return mainGamebox;
	}

	public void setMainGamebox(Gamebox mainGamebox) {
		this.mainGamebox = mainGamebox;
	}

	public Set<Gamebox> getAdditionalGameboxes() {
		return additionalGameboxes;
	}

	public void setAdditionalGameboxes(Set<Gamebox> additionalGameboxes) {
		this.additionalGameboxes = additionalGameboxes;
	}

	@Override
	public String toString() {
		return "Match{" +
				"id=" + id +
				", tutorial=" + tutorial +
				", winType=" + winType +
				", event=" + event +
				", mainGamebox=" + mainGamebox +
				", additionalGameboxes=" + additionalGameboxes +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Match match = (Match) o;

		if (id != match.id) return false;
		if (!tutorial.equals(match.tutorial)) return false;
		if (winType != match.winType) return false;
		if (!event.equals(match.event)) return false;
		if (!mainGamebox.equals(match.mainGamebox)) return false;
		return !(additionalGameboxes != null ? !additionalGameboxes.equals(match.additionalGameboxes) : match.additionalGameboxes != null);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + tutorial.hashCode();
		result = 31 * result + winType.hashCode();
		result = 31 * result + event.hashCode();
		result = 31 * result + mainGamebox.hashCode();
		result = 31 * result + (additionalGameboxes != null ? additionalGameboxes.hashCode() : 0);
		return result;
	}

	public enum WinType {
		BINARY, POINTS;
	}
}
