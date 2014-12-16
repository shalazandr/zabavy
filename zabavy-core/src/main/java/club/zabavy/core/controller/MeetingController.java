package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.Supply;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.MeetingService;
import club.zabavy.core.service.ParticipationService;
import club.zabavy.core.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api")
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private ParticipationService participationService;

	@RequestMapping(value = "/meetings", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting> getMeetings() {
		return meetingService.getAll();
	}

	@RequestMapping(value = "/meetings", method = RequestMethod.POST)
	@ResponseBody
	public Meeting saveMeeting(@RequestBody Meeting meeting) {
		meetingService.insert(meeting);
		return meeting;
	}

	@RequestMapping(value = "/meetings/{meetingId}", method = RequestMethod.GET)
	@ResponseBody
	public Meeting getMeetingById(@PathVariable("meetingId") Long id, HttpServletResponse response) throws IOException {
		Meeting meeting = meetingService.findById(id);
		if(meeting == null) response.sendError(404);
		return meeting;
	}

	@RequestMapping(value = "/meetings/{meetingId}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateMeeting(@PathVariable("meetingId") Long id, @RequestBody Meeting meeting) {
		meeting.setId(id);
		meetingService.update(meeting);
	}

	@RequestMapping(value = "/meetings/{meetingId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteMeeting(@PathVariable("meetingId") Long id) {
		meetingService.remove(id);
	}

	@RequestMapping(value = "/meetings/{meetingId}/participants", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getParticipants(@PathVariable("meetingId") Long id) {
		return participationService.getParticipants(id);
	}

	@RequestMapping(value = "/meetings/{meetingId}/participants", method = RequestMethod.POST)
	@ResponseBody
	public void addParticipant(@PathVariable("meetingId") Long id, @RequestBody User user) {
		participationService.addParticipation(id, user.getId());
	}

	@RequestMapping(value = "/meetings/{meetingId}/participants/{participantId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean existParticipation(@PathVariable("meetingId") Long meetingId, @PathVariable("participantId") Long userId) {
		return participationService.existParticipation(meetingId, userId);
	}

	@RequestMapping(value = "/meetings/{meetingId}/participants/{participantId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void removeParticipation(@PathVariable("meetingId") Long meetingId, @PathVariable("participantId") Long userId) {
		participationService.removeParticipation(meetingId, userId);
	}
}
