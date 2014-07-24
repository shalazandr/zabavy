package club.zabavy.core.service.impl;

import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public List<User> getAll() {
		return userDAO.getAll();
	}

	@Override
	public User findById(long id) {
		return userDAO.findById(id);
	}

	@Override
	public void insert(User user) {
		userDAO.insert(user);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public void remove(long id) {
		userDAO.remove(id);
	}
}
