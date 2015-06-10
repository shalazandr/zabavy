package club.zabavy.core.service.impl;

import club.zabavy.core.dao.SupplyDAO;
import club.zabavy.core.domain.entity.Supply;
import club.zabavy.core.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SupplyServiceImpl implements SupplyService {

	@Autowired
	private SupplyDAO supplyDAO;

	@Override
	public List<Supply> findByParam(Long meetingId, Long userId, Long gameId, Supply.Status status) {
		return supplyDAO.findByParam(meetingId, userId, gameId, status);
	}

	@Override
	public Supply findById(long id) {
		return supplyDAO.findById(id);
	}

	@Override
	public void insert(Supply entity) {
		supplyDAO.insert(entity);
	}

	@Override
	public Supply update(Supply entity) {
		return supplyDAO.update(entity);
	}

	@Override
	public void remove(long id) {
		supplyDAO.remove(id);
	}
}
