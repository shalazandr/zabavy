package club.zabavy.core.service;

import club.zabavy.core.dao.UserDAO;
import club.zabavy.core.domain.entity.User;
import club.zabavy.core.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserDAO userDAO;

	@InjectMocks
	private UserServiceImpl userServiceMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFillingNull(){
		Assert.assertNotNull(userServiceMock);

		User user = new User();
		user.setFirstName("Test");
		user.setLevel(-2);

		userServiceMock.insert(user);

		ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
		Mockito.verify(userDAO).insert(argument.capture());
		Assert.assertEquals("Test", argument.getValue().getFirstName());
		Assert.assertEquals("", argument.getValue().getLastName());
		Assert.assertEquals("Test", argument.getValue().getNickname());
		Assert.assertEquals("/img/icon-user-default.png", argument.getValue().getPhotoUrl());
		Assert.assertEquals(0, argument.getValue().getLevel());
	}

}
