package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.CredentialDAO;
import club.zabavy.core.domain.Vendor;
import club.zabavy.core.domain.entity.Credential;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@SuppressWarnings("JpaQlInspection")
@Transactional
public class HibernateCredentialDAO implements CredentialDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Credential find(Vendor vendor, long vendorUserId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Credential where vendor = :vendor and vendorUserId = :vendorUserId");
		query.setInteger("vendor", vendor.ordinal());
		query.setLong("vendorUserId", vendorUserId);
		return (Credential) query.uniqueResult();
	}

	@Override
	public void insert(Credential credential) {
		sessionFactory.getCurrentSession().save(credential);
	}

	@Override
	public void remove(Vendor vendor, long vendorUserId) {
		Credential credential = find(vendor, vendorUserId);
		if(credential != null) sessionFactory.getCurrentSession().delete(credential);
	}
}
