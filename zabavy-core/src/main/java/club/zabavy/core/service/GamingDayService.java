package club.zabavy.core.service;

import club.zabavy.core.domain.entity.GamingDay;

import java.util.Date;
import java.util.List;

public interface GamingDayService extends BaseService<GamingDay> {
	List<GamingDay> findByParam(Date dateFrom, Date dateTo, int offset, int limit);
}