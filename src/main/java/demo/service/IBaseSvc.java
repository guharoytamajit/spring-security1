package demo.service;

public interface IBaseSvc<T, I> {
	public T save(T object);

	public T update(T object);

	public T findOne(I id);

	Iterable<T> findByAll();

	void deleteAll();

	void delete(I id);
}
