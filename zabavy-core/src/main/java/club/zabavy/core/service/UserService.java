package club.zabavy.core.service;

import club.zabavy.core.domain.entity.User;

import java.util.List;

public interface UserService {
	List<User> getAll();
	User findById(long id);
	void insert(User user);
	void update(User user);
	void remove(long id);

}
