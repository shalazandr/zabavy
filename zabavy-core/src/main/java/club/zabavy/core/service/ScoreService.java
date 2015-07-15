package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Score;

import java.util.List;

public interface ScoreService extends BaseService<Score> {
	List<Score> findByParam(Long matchId, Long userId, Boolean win, int offset, int limit);
}
