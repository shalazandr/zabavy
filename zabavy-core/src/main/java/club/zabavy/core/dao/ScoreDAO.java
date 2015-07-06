package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Score;

import java.util.List;

public interface ScoreDAO extends BaseDAO<Score> {
	List<Score> findByParam(Long matchId, Long userId, Boolean win);
}
