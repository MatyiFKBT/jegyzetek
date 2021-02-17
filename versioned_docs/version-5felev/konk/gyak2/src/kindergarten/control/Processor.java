package kindergarten.control;

import kindergarten.model.KinderGarten;

public interface Processor {
	public KinderGarten process(String data) throws UnnamedKidException;
}
