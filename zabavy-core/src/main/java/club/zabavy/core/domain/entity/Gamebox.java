package club.zabavy.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Gamebox {

	@Id
	@GeneratedValue
	private long id;
	private String ukTitle, enTitle;

	@OneToOne
	private Image cover;

	private String description;
	private short mink, maxk, minTime, maxTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent")
	private Gamebox parent;

	@OneToMany(mappedBy = "parent")
	@JsonIgnore
	private List<Gamebox> addons;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUkTitle() {
		return ukTitle;
	}

	public void setUkTitle(String ukTitle) {
		this.ukTitle = ukTitle;
	}

	public String getEnTitle() {
		return enTitle;
	}

	public void setEnTitle(String enTitle) {
		this.enTitle = enTitle;
	}

	public Image getCover() {
		return cover;
	}

	public void setCover(Image cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getMink() {
		return mink;
	}

	public void setMink(short mink) {
		this.mink = mink;
	}

	public short getMaxk() {
		return maxk;
	}

	public void setMaxk(short maxk) {
		this.maxk = maxk;
	}

	public short getMinTime() {
		return minTime;
	}

	public void setMinTime(short minTime) {
		this.minTime = minTime;
	}

	public short getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(short maxTime) {
		this.maxTime = maxTime;
	}

	public Gamebox getParent() {
		return parent;
	}

	public void setParent(Gamebox parent) {
		this.parent = parent;
	}

	public List<Gamebox> getAddons() {
		return addons;
	}

	public void setAddons(List<Gamebox> addons) {
		this.addons = addons;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Gamebox gamebox = (Gamebox) o;

		if (id != gamebox.id) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (id ^ (id >>> 32));
	}
}
