package club.zabavy.core.domain.entity;

import javax.persistence.Entity;

@Entity
@org.hibernate.annotations.Entity( dynamicUpdate = true )
public class Image extends BaseEntity {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
