package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Gamebox;

import java.util.List;

public interface GameboxDAO extends BaseDAO<Gamebox> {
	List<Gamebox> findByParam(String title, Boolean isAddon, Integer mink, Integer maxk, int offset, int limit);
	List<Gamebox> getAddonsFor(long id);
	void detachAddonsFrom(long id);
}
