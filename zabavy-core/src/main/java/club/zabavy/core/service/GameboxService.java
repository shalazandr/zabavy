package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Gamebox;

import java.util.List;

public interface GameboxService extends BaseService<Gamebox> {
	List<Gamebox> findByParam(String title, Boolean isAddon, Integer mink, Integer maxk);
	List<Gamebox> getAddonsFor(long id);
}
