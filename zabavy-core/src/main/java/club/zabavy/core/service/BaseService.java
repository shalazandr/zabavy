package club.zabavy.core.service;

public interface BaseService<T> {
	T findById(long id);
	void insert(T entity);
	void update(T entity);
	void remove(long id);
}
