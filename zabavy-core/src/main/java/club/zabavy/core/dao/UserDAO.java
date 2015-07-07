package club.zabavy.core.dao;

import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {
	List<User> findByParam(String name, Integer level, Role role);
}
