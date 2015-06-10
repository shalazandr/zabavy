package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.GamingDay;
import club.zabavy.core.service.GamingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class GamingDayController {

	@Autowired
	private GamingDayService gamingDayService;

	@RequestMapping(value = "/days", method = RequestMethod.GET)
	@ResponseBody
	public List<GamingDay> getGamingDays() {
		return gamingDayService.getAll();
	}

	@RequestMapping(value = "/days", method = RequestMethod.POST)
	@ResponseBody
	public GamingDay saveGamingDay(@RequestBody GamingDay gamingDay) {
		gamingDayService.insert(gamingDay);
		return gamingDay;
	}

	@RequestMapping(value = "/days/{gamingDayId}", method = RequestMethod.GET)
	@ResponseBody
	public GamingDay getGamingDayById(@PathVariable("gamingDayId") Long id, HttpServletResponse response) throws IOException {
		GamingDay gamingDay = gamingDayService.findById(id);
		if(gamingDay == null) response.sendError(404);
		return gamingDay;
	}

	@RequestMapping(value = "/days/{gamingDayId}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateGamingDay(@PathVariable("gamingDayId") Long id, @RequestBody GamingDay gamingDay) {
		gamingDay.setId(id);
		gamingDayService.update(gamingDay);
	}

	@RequestMapping(value = "/days/{gamingDayId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGamingDay(@PathVariable("gamingDayId") Long id) {
		gamingDayService.remove(id);
	}

}
