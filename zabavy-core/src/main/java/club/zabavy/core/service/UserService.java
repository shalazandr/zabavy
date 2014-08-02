package club.zabavy.core.service;

import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {
	List<User> getAll();
}
