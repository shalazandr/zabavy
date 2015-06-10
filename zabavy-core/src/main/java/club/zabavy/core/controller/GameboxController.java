package club.zabavy.core.controller;

import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.domain.exceptions.ForbiddenActionException;
import club.zabavy.core.service.AuthService;
import club.zabavy.core.service.GameboxService;
import club.zabavy.core.service.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class GameboxController extends ExceptionHandlingController{

	@Autowired
	AuthService authService;

	@Autowired
	private GameboxService gameboxService;

	@Autowired
	private OwnershipService ownershipService;

	@RequestMapping(value = "/gameboxes", method = RequestMethod.GET)
	@ResponseBody
	public List<Gamebox> findGameboxes( @RequestParam(required = false) String title,
										@RequestParam(required = false) Boolean addon,
										@RequestParam(required = false) Integer mink,
										@RequestParam(required = false) Integer maxk) {
		return gameboxService.findByParam(title, addon, mink, maxk);
	}

	@RequestMapping(value = "/gameboxes", method = RequestMethod.POST)
	@ResponseBody
	public Gamebox saveGamebox(@RequestBody Gamebox gamebox, HttpServletRequest request) {
		User user = authService.getUserFromCookie(request);
		gameboxService.insert(gamebox);
		return gamebox;
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}", method = RequestMethod.GET)
	@ResponseBody
	public Gamebox getGameboxById(@PathVariable("gameboxId") Long id, HttpServletResponse response) throws IOException {
		Gamebox gamebox = gameboxService.findById(id);
		if(gamebox == null) response.sendError(404);
		return gamebox;
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}", method = RequestMethod.PUT)
	@ResponseBody
	public Gamebox updateGamebox(@PathVariable("gameboxId") Long id, @RequestBody Gamebox gamebox, HttpServletRequest request) {
		User user = authService.getUserFromCookie(request);
		gamebox.setId(id);
		return gameboxService.update(gamebox);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGamebox(@PathVariable("gameboxId") Long id, HttpServletRequest request) {
		User user = authService.getUserFromCookie(request);
		if(user.getRole() == Role.USER) throw new ForbiddenActionException("Delete gamebox can only admin or moderator.");
		gameboxService.remove(id);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/addons", method = RequestMethod.GET)
	@ResponseBody
	public List<Gamebox> getGameboxAddons(@PathVariable("gameboxId") Long id) {
		return gameboxService.getAddonsFor(id);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getGameboxOwners(@PathVariable("gameboxId") Long id) {
		return ownershipService.getOwners(id);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners", method = RequestMethod.POST)
	@ResponseBody
	public void addOwnerForGamebox(@PathVariable("gameboxId") Long gameboxId, @RequestBody User user, HttpServletRequest request) {
		User creator = authService.getUserFromCookie(request);
		if(user.getId() > 0){
			if(creator.getId() != user.getId()) {
				if(creator.getRole() == Role.USER) throw new ForbiddenActionException("Create ownerhip for other users can only admin or moderator.");
			}
			ownershipService.createOwnership(gameboxId, user.getId());
		}
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean existOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId) {
		return ownershipService.existOwnership(gameboxId, userId);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId, HttpServletRequest request) {
		User creator = authService.getUserFromCookie(request);
		if(creator.getId() != userId) {
			if(creator.getRole() == Role.USER) throw new ForbiddenActionException("Delete ownerhip for other users can only admin or moderator.");
		}
		ownershipService.deleteOwnership(gameboxId, userId);
	}

}
