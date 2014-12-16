package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Supply;

import java.util.List;

public interface SupplyService extends BaseService<Supply> {
	List<Supply> findByParam(Long meetingId, Long userId, Long gameId, Supply.Status status);
}
