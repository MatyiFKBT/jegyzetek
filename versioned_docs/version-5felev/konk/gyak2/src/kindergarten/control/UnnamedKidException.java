package kindergarten.control;

public class UnnamedKidException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnnamedKidException() {
		super("All kid have to have name");
	}

}
