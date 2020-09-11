package gyak.elso;

public class Main {

	public static void main(String... strings) {
		Concatenator c = new Concatenator();
		c.add("Hello");
		c.add("World");
		System.out.println("Hello wolrd!");
		System.out.println(c.execute());
		System.out.println("-----");
		Command<Integer> command = new AbstractCommand<Integer>() {
			@Override
			public Integer execute() {
				int r = list.get(0);
				for(int i=1; i< list.size(); ++i) {
					r -= list.get(i);
				}
				return r;
			}
		};
		command.add(2);
		command.add(3);
		command.add(4);
		System.out.println(command.execute());
		FunctionalCommand<Integer> mul = (Integer... is) -> {
			int s = 1;
			for(int i:is) {
				s*=i;
			}
			return s;
		};
		
		System.out.println(mul.execute(3,4,5));
	}
	
}
