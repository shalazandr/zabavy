package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Gamebox;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.junit.Assert.*;

public class HibernateGameboxFiltrationDAOTest extends HibernateBaseDAOTest {

	@Autowired
	private GameboxDAO gameboxDAO;

	@Before
	public void insertData(){
		Gamebox parent = new Gamebox();
		parent.setUkTitle("Манчкін");
		parent.setEnTitle("Munchkin");
		gameboxDAO.insert(parent);

		Gamebox addon = new Gamebox();
		addon.setUkTitle("Манчкін 2");
		addon.setEnTitle("Munchkin 2");
		addon.setParent(addon);
		gameboxDAO.insert(addon);

		parent = new Gamebox();
		parent.setUkTitle("Мантре");
		parent.setEnTitle("Muntre");
		gameboxDAO.insert(parent);

		parent = new Gamebox();
		parent.setUkTitle("Тест");
		parent.setEnTitle("Test");
		gameboxDAO.insert(parent);

		addon = new Gamebox();
		addon.setUkTitle("Тест 2");
		addon.setEnTitle("Test 2");
		addon.setParent(addon);
		gameboxDAO.insert(addon);
	}

	@Test
	public void nullFilterTest(){
		List<Gamebox> list = gameboxDAO.findByParam(null, null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(5, list.size());
	}

	@Test
	public void filterByTitleTest(){
		List<Gamebox> addons = gameboxDAO.findByParam("munch", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(2, addons.size());

		addons = gameboxDAO.findByParam("mun", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(3, addons.size());

		addons = gameboxDAO.findByParam("Ман", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(3, addons.size());

		addons = gameboxDAO.findByParam("т", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(3, addons.size());

		addons = gameboxDAO.findByParam("", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(5, addons.size());

		addons = gameboxDAO.findByParam(" " , null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(2, addons.size());

		addons = gameboxDAO.findByParam("і", null, null, null, 0, Integer.MAX_VALUE);
		assertEquals(2, addons.size());
	}

	@Test
	public void filterAddonsTest(){
		List<Gamebox> list = gameboxDAO.findByParam(null, true, null, null, 0, Integer.MAX_VALUE);
		assertEquals(2, list.size());

		list = gameboxDAO.findByParam(null, false, null, null, 0, Integer.MAX_VALUE);
		assertEquals(3, list.size());
	}

}
