package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.GameboxDAO;
import club.zabavy.core.dao.OwnershipDAO;
import club.zabavy.core.domain.entity.Gamebox;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateGameboxDAO implements GameboxDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	OwnershipDAO ownershipDAO;

	@Override
	public List<Gamebox> findByParam(String title, Boolean isAddon, Integer mink, Integer maxk) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Gamebox.class);

		if(title != null) criteria.add( Restrictions.or(
											Restrictions.ilike("ukTitle", title, MatchMode.ANYWHERE),
											Restrictions.ilike("enTitle", title, MatchMode.ANYWHERE)
										));
		if(isAddon != null) {
			if(isAddon == true) {
				criteria.add(Restrictions.isNotNull("parent"));
			} else {
				criteria.add(Restrictions.isNull("parent"));
			}
		}

		return criteria.list();
	}

	@Override
	public Gamebox findById(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Gamebox where id = :id");
		query.setLong("id", id);
		return (Gamebox) query.uniqueResult();
	}

	@Override
	public void insert(Gamebox gamebox) {
		sessionFactory.getCurrentSession().save(gamebox);
	}

	@Override
	public void update(Gamebox gamebox) {
		Gamebox gb = findById(gamebox.getId());

		if(gamebox.getUkTitle() != null) gb.setUkTitle(gamebox.getUkTitle());
		if(gamebox.getEnTitle() != null) gb.setEnTitle(gamebox.getEnTitle());
		if(gamebox.getCover() != null) gb.setCover(gamebox.getCover());
		if(gamebox.getDescription() != null) gb.setDescription(gamebox.getDescription());
		if(gamebox.getMink() > 0) gb.setMink(gamebox.getMink());
		if(gamebox.getMaxk() > 0) gb.setMaxk(gamebox.getMaxk());
		if(gamebox.getMinTime() > 0) gb.setMinTime(gamebox.getMinTime());
		if(gamebox.getMaxTime() > 0) gb.setMaxTime(gamebox.getMaxTime());
		if(gamebox.getParent() != null) gb.setParent(gamebox.getParent());

		sessionFactory.getCurrentSession().update(gb);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Gamebox gamebox = (Gamebox) session.load(Gamebox.class, id);
		if(gamebox != null){
			ownershipDAO.deleteOwnershipsForGamebox(id);
			List<Gamebox> addons = gamebox.getAddons();
			for(Gamebox addon:addons){
				addon.setParent(null);
				session.update(addon);
			}
			session.delete(gamebox);
		}
	}
}
