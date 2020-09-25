package kindergarten.view;

public class KindergartenPrinter implements Printer {

	@Override
	public void printInputText(String text) {
		System.out.println("Data from Gizi:");
		System.out.println(text);
	}

	@Override
	public void printResult(String result) {
		System.out.print("Most popular kid: ");
		System.out.println(result);
		System.out.println();
	}

	@Override
	public void printException(String msg) {
		System.err.print(msg);		
	}

}
