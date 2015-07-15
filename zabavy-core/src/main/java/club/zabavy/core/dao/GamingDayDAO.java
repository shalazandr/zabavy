package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.GamingDay;

import java.util.List;

public interface GamingDayDAO extends BaseDAO<GamingDay>{
	List<GamingDay> getAll(int offset, int limit);
}
