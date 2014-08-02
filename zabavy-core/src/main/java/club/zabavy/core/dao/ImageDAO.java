package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Image;

import java.util.List;

public interface ImageDAO extends BaseDAO<Image>{
	List<Image> getAll();
}
