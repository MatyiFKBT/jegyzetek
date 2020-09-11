package gyak.elso;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand<T> implements Command<T> {

	protected List<T> list = new ArrayList<>();
	
	@Override
	public void add(T t) {
		list.add(t);
	}
	
}
