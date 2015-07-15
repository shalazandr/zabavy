package club.zabavy.core.service.impl;

import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.Role;
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
	public List<User> findByParam(String name, Integer level, Role role, int offset, int limit) {
		return userDAO.findByParam(name, level, role, offset, limit);
	}

	@Override
	public User findById(long id) {
		return userDAO.findById(id);
	}

	@Override
	public void insert(User user) {
		if(user.getFirstName() == null) user.setFirstName("");
		if(user.getLastName() == null) user.setLastName("");
		if(user.getNickname() == null || user.getNickname().isEmpty()) {
			String nickname = user.getFirstName() + " ";
			if(user.getLastName().trim().equals("")) {
				nickname = user.getFirstName();
			} else {
				if(user.getLastName().length() > 2) {
					nickname = nickname + user.getLastName().substring(0, 3) + ".";
				} else {
					nickname = nickname + user.getLastName() + ".";
				}
			}
			user.setNickname(nickname);
		}
		if(user.getRole() == null) user.setRole(Role.USER);
		if(user.getPhotoUrl() == null) user.setPhotoUrl("/img/icon-user-default.png");
		if(user.getLevel() < 0) user.setLevel(0);
		userDAO.insert(user);
	}

	@Override
	public User update(User user) {
		return userDAO.update(user);
	}

	@Override
	public void remove(long id) {
		userDAO.remove(id);
	}
}
