package club.zabavy.core.service.impl;

import club.zabavy.core.dao.ImageDAO;
import club.zabavy.core.domain.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageServiseImpl implements ImageDAO {

	@Autowired
	ImageDAO imageDAO;

	@Override
	public List<Image> getAll() {
		return imageDAO.getAll();
	}

	@Override
	public Image findById(long id) {
		return imageDAO.findById(id);
	}

	@Override
	public void insert(Image image) {
		imageDAO.insert(image);
	}

	@Override
	public void update(Image image) {
		imageDAO.update(image);
	}

	@Override
	public void remove(long id) {
		imageDAO.remove(id);
	}
}
