package club.zabavy.core.service;

import club.zabavy.core.domain.entity.GamingDay;

import java.util.List;

public interface GamingDayService extends BaseService<GamingDay> {
	List<GamingDay> getAll(int offset, int limit);
}