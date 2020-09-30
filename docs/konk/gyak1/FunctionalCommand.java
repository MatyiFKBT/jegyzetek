package gyak.elso;

@FunctionalInterface
public interface FunctionalCommand<T extends Number> {

	T execute(T... t);
}
