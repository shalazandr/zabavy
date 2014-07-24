package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.GameboxService;
import club.zabavy.core.service.OwnershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class GameboxController {

	@Autowired
	private GameboxService gameboxService;

	@Autowired
	private OwnershipService ownershipService;

	@RequestMapping(value = "/gameboxes", method = RequestMethod.GET)
	@ResponseBody
	public List<Gamebox> getGameboxes() {
		return gameboxService.getAll();
	}

	@RequestMapping(value = "/gameboxes", method = RequestMethod.POST)
	@ResponseBody
	public Gamebox saveGamebox(@RequestBody Gamebox gamebox) {
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
	public void updateGamebox(@PathVariable("gameboxId") Long id, @RequestBody Gamebox gamebox) {
		gamebox.setId(id);
		gameboxService.update(gamebox);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteGamebox(@PathVariable("gameboxId") Long id) {
		gameboxService.remove(id);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getGameboxOwners(@PathVariable("gameboxId") Long id) {
		return ownershipService.getOwners(id);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners", method = RequestMethod.POST)
	@ResponseBody
	public void addOwnerForGamebox(@PathVariable("gameboxId") Long gameboxId, @RequestBody User user) {
		if(user.getId() > 0) ownershipService.createOwnership(gameboxId, user.getId());
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean existOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId) {
		return ownershipService.existOwnership(gameboxId, userId);
	}

	@RequestMapping(value = "/gameboxes/{gameboxId}/owners/{userId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteOwnerhip(@PathVariable("userId") Long userId, @PathVariable("gameboxId") Long gameboxId) {
		ownershipService.deleteOwnership(gameboxId, userId);
	}

}
