package club.zabavy.core.service;

public interface BaseService<T> {
	T findById(long id);
	void insert(T entity);
	T update(T entity);
	void remove(long id);
}
