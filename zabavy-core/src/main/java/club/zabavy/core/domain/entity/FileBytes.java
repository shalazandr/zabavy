package club.zabavy.core.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FileBytes {

	@Id
	private Long id;
	@Lob
	private byte[] bytes;

	public FileBytes() {}

	public FileBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public FileBytes(Long id, byte[] bytes) {
		this.id = id;
		this.bytes = bytes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
