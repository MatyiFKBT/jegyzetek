package gyak.elso;

import java.util.StringJoiner;

public class Concatenator extends AbstractCommand<String> {
	
	public String execute() {
		StringJoiner sj = new StringJoiner(" ");
		for(String s: list) {
			sj.add(s);
		}
		return sj.toString()+"!";
	}
}
