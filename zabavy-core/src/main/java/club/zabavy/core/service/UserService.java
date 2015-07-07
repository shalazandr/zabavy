package club.zabavy.core.service;

import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface UserService extends BaseService<User> {
	List<User> findByParam(String name, Integer level, Role role);
}
