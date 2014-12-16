package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Supply;
import club.zabavy.core.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class SupplyController {

	@Autowired
	private SupplyService supplyService;

	@RequestMapping(value = "/supplies", method = RequestMethod.GET)
	@ResponseBody
	public List<Supply> supplies( @RequestParam(value = "meetingId", required = false) Long meetingId,
								@RequestParam(value = "userId", required = false) Long userId,
								@RequestParam(value = "gameId", required = false) Long gameId,
								@RequestParam(value = "status", required = false) Supply.Status status) {
		return supplyService.findByParam(meetingId, userId, gameId, status);
	}

	@RequestMapping(value = "/supplies", method = RequestMethod.PUT)
	@ResponseBody
	public void updateSupply(@RequestParam("supply") Supply supply) {
		supplyService.update(supply);
	}
}
