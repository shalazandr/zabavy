package club.zabavy.core.dao;

import club.zabavy.core.domain.Role;
import club.zabavy.core.domain.entity.Meeting;
import club.zabavy.core.domain.entity.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.*;

public class HibernateUserDAOTest extends HibernateBaseDAOTest {

	@Autowired
	UserDAO userDAO;

	@Autowired
	MeetingDAO meetingDAO;

	@Autowired(required=true)
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Test
	public void insertUserTest(){
		User user = new User();
		user.setFirstName("First");
		user.setLastName("Last");
		user.setNickname("nick");
		user.setLevel(3);
		user.setRole(Role.USER);
		userDAO.insert(user);

		assertTrue(user.getId() > 0);

		user = userDAO.findById(user.getId());

		assertNotNull(user.getLastName());
		assertEquals("First", user.getFirstName());
		assertEquals("Last", user.getLastName());
		assertEquals("nick", user.getNickname());
		assertEquals(3, user.getLevel());
		assertEquals(Role.USER, user.getRole());
	}

	@Test
	public void updateUserTest(){
		long userID;
		User user = new User();
		user.setFirstName("Petro");
		user.setLastName("Tre");
		user.setNickname("nick");
		user.setLevel(7);
		userDAO.insert(user);
		userID = user.getId();

		user = new User();
		user.setId(userID);
		user.setPhotoUrl("jhsgdf/sdf.ong");
		userDAO.update(user);

		user = userDAO.findById(userID);
		assertNotNull(user.getNickname());
		assertNotNull(user.getFirstName());
		assertNotNull(user.getLastName());
		assertTrue(user.getLevel() == 7);
		assertEquals("jhsgdf/sdf.ong", user.getPhotoUrl());
	}

	@Test
	public void deleteUserTest(){
		User user = new User();
		user.setFirstName("First");
		user.setLastName("Last");
		userDAO.insert(user);
		assertTrue(user.getId() > 0);
		user = userDAO.findById(user.getId());
		assertNotNull(user);

		userDAO.remove(user.getId());
		user = userDAO.findById(user.getId());
		assertNull(user);
	}

	@Test
	public void deleteInitiatorTest(){
		User user = new User();
		user.setFirstName("First");
		user.setLastName("Last");
		userDAO.insert(user);
		assertTrue(user.getId() > 0);
		user = userDAO.findById(user.getId());
		assertNotNull(user);

		Meeting meeting = new Meeting();
		meeting.setTitle("Test");
		meeting.setInitiator(user);
		meetingDAO.insert(meeting);

		meeting = meetingDAO.findById(meeting.getId());
		assertNotNull(meeting.getInitiator());

		userDAO.remove(user.getId());
		user = userDAO.findById(user.getId());
		assertNull(user);

		sessionFactory.getCurrentSession().clear(); //cleaning Hibernate cache

		meeting = meetingDAO.findById(meeting.getId());
		assertNull(meeting.getInitiator());
	}
}
