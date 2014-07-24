package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Gamebox;

import java.util.List;

public interface GameboxDAO {
	List<Gamebox> getAll();
	Gamebox findById(long id);
	void insert(Gamebox gamebox);
	void update(Gamebox gamebox);
	void remove(long id);
}
