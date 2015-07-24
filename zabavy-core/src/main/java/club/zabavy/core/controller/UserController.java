package club.zabavy.core.controller;

import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.domain.exceptions.ForbiddenActionException;
import club.zabavy.core.service.AuthService;
import club.zabavy.core.service.OwnershipService;
import club.zabavy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/api")
public class UserController {

	@Autowired
	AuthService authService;

	@Autowired
	private UserService userService;

	@Autowired
	private OwnershipService ownershipService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> findUsers(@RequestParam(required = false) String name,
								@RequestParam(required = false) Integer level,
								@RequestParam(required = false) Role role,
								@RequestParam(defaultValue = "0") int offset,
								@RequestParam(defaultValue = "21") int limit) throws UnsupportedEncodingException {
		if(name != null) {
			name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		}
		return userService.findByParam(name, level, role, offset, limit);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public User saveUser(@RequestBody User user, HttpServletRequest request) {
		User currentUser = authService.getUserFromCookie(request);
		if(currentUser.getRole() == Role.ADMIN) userService.insert(user);
		else throw new ForbiddenActionException("Only admins can add new user");
		return user;
	}

	@RequestMapping(value = "/users/current", method = RequestMethod.GET)
	@ResponseBody
	public User getCurrentUser(HttpServletRequest request) {
		User currentUser = authService.getUserFromCookie(request);
		return userService.findById(currentUser.getId());
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
	public User updateUser(@PathVariable("userId") Long id, @RequestBody User user, HttpServletRequest request) {
		User currentUser = authService.getUserFromCookie(request);
		if(currentUser.getRole() == Role.ADMIN || currentUser.getId() == id) {
			user.setId(id);
			user = userService.update(user);
		} else throw new ForbiddenActionException();

		return user;
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable("userId") Long id, HttpServletRequest request) {
		User currentUser = authService.getUserFromCookie(request);
		if(currentUser.getRole() == Role.ADMIN) userService.remove(id);
		else throw new ForbiddenActionException("Only admins can delete user");
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
