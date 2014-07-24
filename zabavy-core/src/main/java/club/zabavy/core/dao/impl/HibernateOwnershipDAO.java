package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.OwnershipDAO;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.Ownership;
import club.zabavy.core.domain.entity.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateOwnershipDAO implements OwnershipDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<User> getOwners(long gameboxId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where id in (select userId from Ownership where gameboxId = :gid)");
		query.setLong("gid", gameboxId);
		return query.list();
	}

	@Override
	public List<Gamebox> getGameboxes(long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Gamebox where id in (select gameboxId from Ownership where userId = :uid)");
		query.setLong("uid", userId);
		return query.list();
	}

	@Override
	public boolean existOwnership(long gameboxId, long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Ownership where gameboxId = :gid and userId = :uid");
		query.setLong("gid", gameboxId);
		query.setLong("uid", userId);
		return query.uniqueResult() != null;
	}

	@Override
	public void createOwnership(long gameboxId, long userId) {
		Ownership ownership = new Ownership();
		ownership.setGameboxId(gameboxId);
		ownership.setUserId(userId);
		sessionFactory.getCurrentSession().save(ownership);
	}

	@Override
	public void deleteOwnership(long gameboxId, long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Ownership where gameboxId = :gid and userId = :uid");
		query.setLong("gid", gameboxId);
		query.setLong("uid", userId);
		query.executeUpdate();
	}

	@Override
	public void deleteOwnershipsForUser(long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Ownership where userId = :uid");
		query.setLong("uid", userId);
		query.executeUpdate();
	}

	@Override
	public void deleteOwnershipsForGamebox(long gameboxId) {
		Query query = sessionFactory.getCurrentSession().createQuery("delete from Ownership where gameboxId = :gid");
		query.setLong("gid", gameboxId);
		query.executeUpdate();
	}


}
