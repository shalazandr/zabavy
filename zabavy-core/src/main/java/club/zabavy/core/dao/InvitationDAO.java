package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Invitation;

import java.util.List;

public interface InvitationDAO extends BaseDAO<Invitation> {
	List<Invitation> findByParam(Long meetingId, Long userId, Invitation.Status status);
}
