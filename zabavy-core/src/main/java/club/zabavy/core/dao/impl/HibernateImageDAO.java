package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.ImageDAO;
import club.zabavy.core.domain.entity.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SuppressWarnings("JpaQlInspection")
public class HibernateImageDAO implements ImageDAO{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Image> getAll() {
		Query query = sessionFactory.getCurrentSession().createQuery("from Image");
		return query.list();
	}

	@Override
	public Image findById(long id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Image where id = :id");
		query.setLong("id", id);
		return (Image) query.uniqueResult();
	}

	@Override
	public void insert(Image image) {
		sessionFactory.getCurrentSession().save(image);
	}

	@Override
	public void update(Image image) {
		Image i = findById(image.getId());

		if(image.getUrl() != null) i.setUrl(image.getUrl());

		sessionFactory.getCurrentSession().update(i);
	}

	@Override
	public void remove(long id) {
		Session session = sessionFactory.getCurrentSession();
		Image image = (Image) session.load(Image.class, id);
		if(image != null) session.delete(image);
	}
}
