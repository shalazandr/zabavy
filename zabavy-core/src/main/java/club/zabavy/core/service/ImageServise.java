package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Image;

import java.util.List;

public interface ImageServise extends BaseService<Image> {
	List<Image> getAll();
}
