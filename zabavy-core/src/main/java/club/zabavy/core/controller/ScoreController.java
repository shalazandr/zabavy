package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Score;
import club.zabavy.core.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ScoreController {

	@Autowired
	private ScoreService scoreService;

	@RequestMapping(value = "/scores", method = RequestMethod.GET)
	@ResponseBody
	public List<Score> findScores(@RequestParam(required = false) Long matchId,
								  @RequestParam(required = false) Long userId,
								  @RequestParam(required = false) Boolean win) {
		return scoreService.findByParam(matchId, userId, win);
	}

	@RequestMapping(value = "/matches/{matchId}/scores", method = RequestMethod.GET)
	@ResponseBody
	public List<Score> getMatchScores(@PathVariable("matchId") Long matchId, HttpServletResponse response) throws IOException {
		return scoreService.findByParam(matchId, null, null);
	}

	@RequestMapping(value = "/scores", method = RequestMethod.POST)
	@ResponseBody
	public Score saveScore(@RequestBody Score score) {
		scoreService.insert(score);
		return score;
	}

	@RequestMapping(value = "/scores/{scoreId}", method = RequestMethod.GET)
	@ResponseBody
	public Score getScoreById(@PathVariable("scoreId") Long id, HttpServletResponse response) throws IOException {
		Score score = scoreService.findById(id);
		if (score == null) response.sendError(404);
		return score;
	}

	@RequestMapping(value = "/scores/{scoreId}", method = RequestMethod.PUT)
	@ResponseBody
	public Score updateScore(@PathVariable("scoreId") Long id, @RequestBody Score score) {
		score.setId(id);
		return scoreService.update(score);
	}

	@RequestMapping(value = "/scores/{scoreId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteScore(@PathVariable("scoreId") Long id) {
		scoreService.remove(id);
	}
}
