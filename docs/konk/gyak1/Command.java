package gyak.elso;

public interface Command<T> {

	void add(T t);
	T execute();
	
}
