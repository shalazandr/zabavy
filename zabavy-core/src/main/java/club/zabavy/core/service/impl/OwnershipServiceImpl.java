package club.zabavy.core.service.impl;

import club.zabavy.core.dao.OwnershipDAO;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OwnershipServiceImpl implements OwnershipService {

	@Autowired
	OwnershipDAO ownershipDAO;

	@Override
	public List<User> getOwners(long gameboxId) {
		return ownershipDAO.getOwners(gameboxId);
	}

	@Override
	public List<Gamebox> getGameboxes(long userId) {
		return ownershipDAO.getGameboxes(userId);
	}

	@Override
	public boolean existOwnership(long gameboxId, long userId) {
		return ownershipDAO.existOwnership(gameboxId, userId);
	}

	@Override
	public void createOwnership(long gameboxId, long userId) {
		ownershipDAO.createOwnership(gameboxId, userId);
	}

	@Override
	public void deleteOwnership(long gameboxId, long userId) {
		ownershipDAO.deleteOwnership(gameboxId, userId);
	}

	@Override
	public void deleteOwnershipsForUser(long userId) {
		ownershipDAO.deleteOwnershipsForUser(userId);
	}

	@Override
	public void deleteOwnershipsForGamebox(long gameboxId) {
		ownershipDAO.deleteOwnershipsForGamebox(gameboxId);
	}
}
