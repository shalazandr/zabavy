package club.zabavy.core.domain.entity;

import club.zabavy.core.domain.Vendor;

import javax.persistence.*;

@Entity
public class Credential extends BaseEntity {

	private long vendorUserId;
	private Vendor vendor;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	public Credential() {}

	public Credential(User user, Vendor vendor, long vendorUserId) {
		this.vendorUserId = vendorUserId;
		this.vendor = vendor;
		this.user = user;
	}

	public long getVendorUserId() {
		return vendorUserId;
	}

	public void setVendorUserId(long vendorUserId) {
		this.vendorUserId = vendorUserId;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Credential{" +
				"id=" + id +
				", user=" + user +
				", vendor=" + vendor +
				", vendorUserId=" + vendorUserId +
				'}';
	}
}
