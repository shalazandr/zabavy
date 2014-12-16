package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Supply;

import java.util.List;

public interface SupplyDAO extends BaseDAO<Supply> {
	List<Supply> findByParam(Long meetingId, Long userId, Long gameId, Supply.Status status);
}
