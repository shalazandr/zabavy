package club.zabavy.core.service.impl;

import club.zabavy.core.dao.MatchDAO;
import club.zabavy.core.dao.ScoreDAO;
import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.entity.Match;
import club.zabavy.core.domain.entity.Score;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ScoreServiceImpl implements ScoreService{

	@Autowired
	private ScoreDAO scoreDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private MatchDAO matchDAO;

	@Override
	public Score findById(long id) {
		return scoreDAO.findById(id);
	}

	@Override
	public void insert(Score entity) {
		User user = entity.getUser();
		if(user == null) throw new NullPointerException("User should be specified!");
		Match match = entity.getMatch();
		if(match == null) throw new NullPointerException("Match should be specified!");

		List<Score> scores = scoreDAO.findByParam(match.getId(), user.getId(), null);

		if(scores.size() > 0) throw new EntityExistsException("Score for specified user and match already exist");
		user = userDAO.findById(user.getId());
		if(user == null) throw new NoSuchElementException("Can't find specified user");
		match = matchDAO.findById(match.getId());
		if(match == null) throw new NoSuchElementException("Can't find specified match");

		scoreDAO.insert(entity);
	}

	@Override
	public Score update(Score entity) {
		Score old = scoreDAO.findById(entity.getId());
		if (old == null) throw new NoSuchElementException("Can't find score with such id");
		old.setPoints(entity.getPoints());
		old.setWin(entity.isWin());
		scoreDAO.update(old);
		return old;
	}

	@Override
	public void remove(long id) {
		Score score = scoreDAO.findById(id);
		if (score == null) throw new NoSuchElementException("Can't find score with such id");
		scoreDAO.remove(score.getId());
	}

	@Override
	public List<Score> findByParam(Long matchId, Long userId, Boolean win) {
		return scoreDAO.findByParam(matchId, userId, win);
	}
}
