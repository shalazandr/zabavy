package club.zabavy.core.service.impl;

import club.zabavy.core.dao.GameboxDAO;
import club.zabavy.core.dao.GamingDayDAO;
import club.zabavy.core.dao.MatchDAO;
import club.zabavy.core.domain.entity.Gamebox;
import club.zabavy.core.domain.entity.GamingDay;
import club.zabavy.core.domain.entity.Match;
import club.zabavy.core.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class MatchServiceImpl implements MatchService{

	@Autowired
	private MatchDAO matchDAO;

	@Autowired
	private GamingDayDAO gamingDayDAO;

	@Autowired
	private GameboxDAO gameboxDAO;

	@Override
	public Match findById(long id) {
		return matchDAO.findById(id);
	}

	@Override
	public void insert(Match entity) {
		GamingDay gamingDay = entity.getEvent();
		if(gamingDay == null) throw new NullPointerException("Gaming day should be specified!");
		gamingDay = gamingDayDAO.findById(gamingDay.getId());
		if(gamingDay == null) throw new NoSuchElementException("Can't find specified gaming day");

		Gamebox gamebox = entity.getMainGamebox();
		if(gamebox == null) throw new NullPointerException("Main gamebox should be specified!");
		gamebox = gameboxDAO.findById(gamebox.getId());
		if(gamebox == null) throw new NoSuchElementException("Can't find specified main gamebox");

		if(entity.getAdditionalGameboxes().size() > 0) {
			Iterator<Gamebox> iterator = entity.getAdditionalGameboxes().iterator();
			while (iterator.hasNext()) {
				gamebox = gameboxDAO.findById(iterator.next().getId());
				if(gamebox == null) throw new NoSuchElementException("Can't find on of specified additional gameboxes");
			}
		}
		matchDAO.insert(entity);
	}

	@Override
	public Match update(Match entity) {
		Match old = matchDAO.findById(entity.getId());
		if(old == null) {
			throw new NoSuchElementException();
		}
		return matchDAO.update(entity);
	}

	@Override
	public void remove(long id) {
		Match old = matchDAO.findById(id);
		if(old == null) {
			throw new NoSuchElementException();
		}
		matchDAO.remove(id);
	}

	@Override
	public List<Match> findByParam(Long eventId, Long gameboxId, Boolean isTutorial) {
		return matchDAO.findByParam(eventId, gameboxId, isTutorial);
	}
}
