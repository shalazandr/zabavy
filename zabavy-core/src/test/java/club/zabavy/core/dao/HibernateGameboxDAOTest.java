package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.Gamebox;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateGameboxDAOTest extends HibernateBaseDAOTest {

	@Autowired
	private GameboxDAO gameboxDAO;

	@Test
	public void insertSingleGameboxTest() {
		Gamebox gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін");
		gamebox.setEnTitle("Munchkin");
		gamebox.setDescription("");
		gamebox.setMink((short) 2);
		gamebox.setMaxk((short) 6);
		gamebox.setMinTime((short) 30);
		gamebox.setMaxTime((short) 90);
		gameboxDAO.insert(gamebox);

		assertTrue(gamebox.getId() > 0);

		gamebox = gameboxDAO.findById(gamebox.getId());

		assertEquals("Манчкін", gamebox.getUkTitle());
		assertEquals("Munchkin", gamebox.getEnTitle());
		assertEquals("", gamebox.getDescription());
		assertEquals(2, gamebox.getMink());
		assertEquals(6, gamebox.getMaxk());
		assertEquals(30, gamebox.getMinTime());
		assertEquals(90, gamebox.getMaxTime());

		assertNull(gamebox.getParent());
	}

	@Test
	public void insertSingleAndAddonGameboxTest() {
		Gamebox gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін");
		gamebox.setEnTitle("Munchkin");
		gameboxDAO.insert(gamebox);

		long singleGameboxId = gamebox.getId();
		Gamebox parentGamebox = new Gamebox();
		parentGamebox.setId(singleGameboxId);
		parentGamebox.setUkTitle("wrong!");

		gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін 2");
		gamebox.setEnTitle("Munchkin 2");
		gamebox.setParent(parentGamebox);
		gameboxDAO.insert(gamebox);

		gamebox = gameboxDAO.findById(gamebox.getId());

		assertEquals("Манчкін 2", gamebox.getUkTitle());
		assertEquals("Munchkin 2", gamebox.getEnTitle());
		assertNotNull(gamebox.getParent());
		assertEquals(singleGameboxId, gamebox.getParent().getId());

		parentGamebox = gameboxDAO.findById(singleGameboxId);
		assertEquals("Манчкін", parentGamebox.getUkTitle());

		List<Gamebox> addons = gameboxDAO.getAddonsFor(singleGameboxId);
		assertTrue(addons.size() == 1);
		assertEquals(gamebox.getId(), addons.get(0).getId());
		assertEquals("Манчкін 2", addons.get(0).getUkTitle());
	}

	@Test
	public void updateGameboxTest() {
		Gamebox gamebox = new Gamebox();
		gamebox.setUkTitle("Мчкін");
		gamebox.setEnTitle("Munchkin");
		gamebox.setDescription("");
		gamebox.setMink((short) 2);
		gamebox.setMaxk((short) 6);
		gamebox.setMinTime((short) 30);
		gamebox.setMaxTime((short) 90);
		gameboxDAO.insert(gamebox);

		long id = gamebox.getId();
		assertTrue(id > 0);

		gamebox = new Gamebox();
		gamebox.setId(id);
		gamebox.setUkTitle("Манчкін");
		gamebox.setDescription("Funny party-game.");
		gameboxDAO.update(gamebox);

		gamebox = gameboxDAO.findById(id);
		assertEquals("Манчкін", gamebox.getUkTitle());
		assertEquals("Munchkin", gamebox.getEnTitle());
		assertEquals("Funny party-game.", gamebox.getDescription());
		assertEquals(2, gamebox.getMink());
		assertEquals(6, gamebox.getMaxk());
		assertEquals(30, gamebox.getMinTime());
		assertEquals(90, gamebox.getMaxTime());

		assertNull(gamebox.getParent());
	}

	@Test
	public void simpleRemoveGameboxTest() {
		Gamebox gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін");
		gamebox.setEnTitle("Munchkin");
		gameboxDAO.insert(gamebox);

		gameboxDAO.remove(gamebox.getId());

		gamebox = gameboxDAO.findById(gamebox.getId());

		assertNull(gamebox);
	}

	@Test
	public void removeGameboxThatHasAddonTest() {
		Gamebox gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін");
		gamebox.setEnTitle("Munchkin");
		gameboxDAO.insert(gamebox);

		long singleGameboxId = gamebox.getId();
		Gamebox parentGamebox = new Gamebox();
		parentGamebox.setId(singleGameboxId);

		gamebox = new Gamebox();
		gamebox.setUkTitle("Манчкін 2");
		gamebox.setEnTitle("Munchkin 2");
		gamebox.setParent(parentGamebox);
		gameboxDAO.insert(gamebox);

		assertTrue(gameboxDAO.getAddonsFor(singleGameboxId).size() > 0);
		gameboxDAO.remove(singleGameboxId);

		parentGamebox = gameboxDAO.findById(singleGameboxId);
		assertNull(parentGamebox);

		assertEquals(0, gameboxDAO.getAddonsFor(singleGameboxId).size());
	}
}
