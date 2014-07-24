package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Gamebox;

import java.util.List;

public interface GameboxService {
	List<Gamebox> getAll();
	Gamebox findById(long id);
	void insert(Gamebox gamebox);
	void update(Gamebox gamebox);
	void remove(long id);
}
