package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Presence;

import java.util.List;

public interface PresenceService extends BaseService<Presence> {
	List<Presence> findByParam(Long gamingDayId, Long userId, Boolean isEnded);
}
