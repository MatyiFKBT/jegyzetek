package gyak.elso;

public class AddCommand extends AbstractCommand<Integer> {

	@Override
	public Integer execute() {
		int sum = 0;
		for(int i: list) {
			sum += i;
		}
		return sum;
	}

}
