package club.zabavy.core.service.impl;

import club.zabavy.core.dao.GameboxDAO;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.service.GameboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameboxServiceImpl implements GameboxService {

	@Autowired
	GameboxDAO gameboxDAO;

	@Override
	public List<Gamebox> getAll() {
		return gameboxDAO.getAll();
	}

	@Override
	public Gamebox findById(long id) {
		return gameboxDAO.findById(id);
	}

	@Override
	public void insert(Gamebox gamebox) {
		gameboxDAO.insert(gamebox);
	}

	@Override
	public void update(Gamebox gamebox) {
		gameboxDAO.update(gamebox);
	}

	@Override
	public void remove(long id) {
		gameboxDAO.remove(id);
	}
}
