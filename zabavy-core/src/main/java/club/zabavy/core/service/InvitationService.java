package club.zabavy.core.service;

import club.zabavy.core.domain.entity.Invitation;

import java.util.List;

public interface InvitationService extends BaseService<Invitation> {
	List<Invitation> findByParam(Long meetingId, Long userId, Invitation.Status status);
}
