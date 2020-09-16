package kindergarten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import kindergarten.control.KinderGartenCalculator;
import kindergarten.control.UnnamedKidException;
import kindergarten.view.KindergartenPrinter;
import kindergarten.view.Printer;

public class Controller {

	private Printer printer = new KindergartenPrinter();
	private KinderGartenCalculator calc = new KinderGartenCalculator();

	public InputStream getInput(int testCase) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream("res\\test" + testCase + ".txt");
		return inputStream;
	}

	public void test(int testCases) {
		for (int i = 0; i < testCases; i++) {
			try (InputStream input = getInput(i)) {
				printer.printResult(calc.run(input));
			} catch (FileNotFoundException e) {
				printer.printException(e.getMessage());
				continue;
			} catch (IOException e1) {
				printer.printException(e1.getMessage());
				continue;
			} catch (UnnamedKidException e2) {
				printer.printException(e2.getMessage());
				continue;
			}
		}
	}
}
