package club.zabavy.core.domain.entity;

import javax.persistence.Entity;

@Entity
public class File extends BaseEntity {

	private Status status;
	private String filename, url;
	private Long vkId;

	public File() {
	}

	public File(String filename) {
		this.filename = filename;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getVkId() {
		return vkId;
	}

	public void setVkId(Long vkId) {
		this.vkId = vkId;
	}

	public enum Status {
		NEW, BACKUPING, BACKUPED
	}
}
