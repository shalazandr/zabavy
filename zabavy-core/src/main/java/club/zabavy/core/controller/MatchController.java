package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Match;
import club.zabavy.core.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@RequestMapping(value = "/matches", method = RequestMethod.GET)
	@ResponseBody
	public List<Match> findMatches( @RequestParam(required = false) Long eventId,
									@RequestParam(required = false) Long gameboxId,
									@RequestParam(required = false) Boolean isTutorial,
									@RequestParam(defaultValue = "0") int offset,
									@RequestParam(defaultValue = "21") int limit) {
		return matchService.findByParam(eventId, gameboxId, isTutorial, offset, limit);
	}

	@RequestMapping(value = "/matches", method = RequestMethod.POST)
	@ResponseBody
	public Match saveMatch(@RequestBody Match match) {
		matchService.insert(match);
		return match;
	}

	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.GET)
	@ResponseBody
	public Match getMatchById(@PathVariable("matchId") Long id, HttpServletResponse response) throws IOException {
		Match match = matchService.findById(id);
		if(match == null) response.sendError(404);
		return match;
	}

	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.PUT)
	@ResponseBody
	public Match updateMatch(@PathVariable("matchId") Long id, @RequestBody Match match) {
		match.setId(id);
		return matchService.update(match);
	}

	@RequestMapping(value = "/matches/{matchId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMatch(@PathVariable("matchId") Long id) {
		matchService.remove(id);
	}
}
