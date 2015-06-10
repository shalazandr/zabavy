package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.OwnershipService;
import club.zabavy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OwnershipService ownershipService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getUsers() {
		return userService.getAll();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public User saveUser(@RequestBody User user) {
		userService.insert(user);
		return user;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable("userId") Long id, HttpServletResponse response) throws IOException {
		User user = userService.findById(id);
		if(user == null) response.sendError(404);
		return user;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@PathVariable("userId") Long id, @RequestBody User user) {
		user.setId(id);
		return userService.update(user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") Long id) {
		userService.remove(id);
	}

	@RequestMapping(value = "/users/{userId}/gameboxes", method = RequestMethod.GET)
	@ResponseBody
	public List<Gamebox> getUserGameboxes(@PathVariable("userId") Long id) {
		return ownershipService.getGameboxes(id);
	}

	@RequestMapping(value = "/users/{userId}/gameboxes", method = RequestMethod.POST)
	@ResponseBody
	public void addGameboxToUser(@PathVariable("userId") Long userId, @RequestBody Gamebox gamebox) {
		if(gamebox.getId() > 0) ownershipService.createOwnership(gamebox.getId(), userId);
	}

	@RequestMapping(value = "/users/{userId}/gameboxes/{gameboxId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean existOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId) {
		return ownershipService.existOwnership(gameboxId, userId);
	}

	@RequestMapping(value = "/users/{userId}/gameboxes/{gameboxId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId) {
		ownershipService.deleteOwnership(gameboxId, userId);
	}
}
