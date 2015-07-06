package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Match;

import java.util.List;

public interface MatchDAO extends BaseDAO<Match> {
	List<Match> findByParam(Long eventId, Long gameboxId, Boolean isTutorial);
}
