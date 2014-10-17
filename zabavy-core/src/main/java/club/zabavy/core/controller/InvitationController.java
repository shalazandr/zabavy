package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Invitation;
import club.zabavy.core.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class InvitationController {

	@Autowired
	private InvitationService invitationService;

	@RequestMapping(value = "/invitations", method = RequestMethod.GET)
	@ResponseBody
	public List<Invitation> findInvitations(@RequestParam(required = false) Long meetingId,
											@RequestParam(required = false) Long userId,
											@RequestParam(required = false) Invitation.Status status) {
		return invitationService.findByParam(meetingId, userId, status);
	}

	@RequestMapping(value = "/invitations", method = RequestMethod.POST)
	@ResponseBody
	public Invitation saveInvitation(@RequestBody Invitation invitation) {
		invitationService.insert(invitation);
		return invitation;
	}

	@RequestMapping(value = "/invitations/{invitationId}", method = RequestMethod.GET)
	@ResponseBody
	public Invitation getInvitationById(@PathVariable("invitationId") Long id, HttpServletResponse response) throws IOException {
		Invitation invitation = invitationService.findById(id);
		if(invitation == null) response.sendError(404);
		return invitation;
	}

	@RequestMapping(value = "/invitations/{invitationId}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateInvitation(@PathVariable("invitationId") Long id, @RequestBody Invitation invitation) {
		invitation.setId(id);
		invitationService.update(invitation);
	}

	@RequestMapping(value = "/invitations/{invitationId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteInvitation(@PathVariable("invitationId") Long id) {
		invitationService.remove(id);
	}
}
