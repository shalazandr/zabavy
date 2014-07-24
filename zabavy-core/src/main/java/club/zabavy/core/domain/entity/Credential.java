package club.zabavy.core.domain.entity;

import club.zabavy.core.domain.Vendor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credential {
	@Id
	private long id;
	private int vendorUserId;
	private Vendor vendor;
//	private User user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVendorUserId() {
		return vendorUserId;
	}

	public void setVendorUserId(int vendorUserId) {
		this.vendorUserId = vendorUserId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
}
