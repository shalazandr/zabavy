package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Match;

import java.util.List;

public interface MatchService extends BaseService<Match> {
	List<Match> findByParam(Long eventId, Long gameboxId, Boolean isTutorial, int offset, int limit);
}
