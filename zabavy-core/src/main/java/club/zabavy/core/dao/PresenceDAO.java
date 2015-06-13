package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Presence;

import java.util.List;

public interface PresenceDAO extends BaseDAO<Presence> {
	List<Presence> findByParam(Long gamingDayId, Long userId, Boolean isEnded);
}
