package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface OwnershipService {
	List<User> getOwners(long gameboxId);
	List<Gamebox> getGameboxes(long userId);
	boolean existOwnership(long gameboxId, long userId);
	void createOwnership(long gameboxId, long userId);
	void deleteOwnership(long gameboxId, long userId);
	void deleteOwnershipsForUser(long userId);
	void deleteOwnershipsForGamebox(long gameboxId);
}
