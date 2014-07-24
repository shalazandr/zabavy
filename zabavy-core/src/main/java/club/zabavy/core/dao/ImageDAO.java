package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Image;

import java.util.List;

public interface ImageDAO {
	List<Image> getAll();
	Image findById(long id);
	void insert(Image image);
	void update(Image image);
	void remove(long id);
}
