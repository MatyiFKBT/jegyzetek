package kindergarten.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kindergarten.control.UnnamedKidException;

public class KinderGarten {

	private List<Kid> kids = new ArrayList<>();

	public boolean add(Kid kid) {
		boolean ans = !kids.contains(kid);
		if (ans) {
			kids.add(kid);
		}
		return ans;
	}

	public Kid getMostPopularKid() {
		Collections.sort(kids);
		return kids.get(0);
	}

	public List<Kid> getKids() {
		return kids;
	}

	public Kid getKid(String name) throws UnnamedKidException {
		if (name.trim().equals("") || name == null) {
			throw new UnnamedKidException();
		}
		for (Kid k : kids) {
			if (k.getName().equals(name)) {
				return k;
			}
		}
		return new Kid(name);
	}

}
