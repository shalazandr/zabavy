package club.zabavy.core.dao;

public interface BaseDAO<T> {
	T findById(long id);
	void insert(T entity);
	void update(T entity);
	void remove(long id);
}
