package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Presence;
import club.zabavy.core.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PresenceController {

	@Autowired
	private PresenceService presenceService;

	@RequestMapping(value = "/presence", method = RequestMethod.POST)
	@ResponseBody
	public Presence savePresence(@RequestBody Presence presence) {
		presenceService.insert(presence);
		return presence;
	}

	@RequestMapping(value = "/presence/{presenceId}", method = RequestMethod.GET)
	@ResponseBody
	public Presence getPresenceById(@PathVariable("presenceId") Long id, HttpServletResponse response) throws IOException {
		Presence presence = presenceService.findById(id);
		if(presence == null) response.sendError(404);
		return presence;
	}

	@RequestMapping(value = "/presence/{presenceId}", method = RequestMethod.PUT)
	@ResponseBody
	public Presence updatePresence(@PathVariable("presenceId") Long id, @RequestBody Presence presence) {
		presence.setId(id);
		return presenceService.update(presence);
	}

	@RequestMapping(value = "/presence/{presenceId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePresence(@PathVariable("presenceId") Long id) {
		presenceService.remove(id);
	}

	@RequestMapping(value = "/presence", method = RequestMethod.GET)
	@ResponseBody
	public List<Presence> findPresence( @RequestParam(value = "userId", required = false) Long userId,
										@RequestParam(value = "dayId", required = false) Long dayId,
										@RequestParam(value = "ended", required = false) Boolean ended,
										@RequestParam(defaultValue = "0") int offset,
										@RequestParam(defaultValue = "21") int limit) {

		return presenceService.findByParam(dayId, userId, ended, offset, limit);
	}
}
