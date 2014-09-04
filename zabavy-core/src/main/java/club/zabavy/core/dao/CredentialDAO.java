package club.zabavy.core.dao;

import club.zabavy.core.domain.Vendor;
import club.zabavy.core.domain.entity.Credential;

public interface CredentialDAO {
	Credential find(Vendor vendor, long vendorUserId);
	void insert(Credential credential);
	void remove(Vendor vendor, long vendorUserId);
}
