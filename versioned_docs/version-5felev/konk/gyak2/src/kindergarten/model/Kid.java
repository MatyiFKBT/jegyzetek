package kindergarten.model;

import java.util.HashSet;
import java.util.Set;

public class Kid implements Comparable<Kid> {

	private String name;
	private int popularity = 0;
	private Set<Kid> likedKids = new HashSet<>();

	public Kid(String name) {
		this.name = name;
	}

	public void like(Kid kid) {
		if (!this.equals(kid)) {
			likedKids.add(kid);
		}
	}

	public void incrasePopularity() {
		popularity++;
	}

	public Set<Kid> getLikedKids() {
		return likedKids;
	}

	public int getPopularity() {
		return popularity;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Kid other = (Kid) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String ans = name + " (" + popularity + ")";
		return ans;
	}

	@Override
	public int compareTo(Kid otherKid) {
		if (this.popularity > otherKid.popularity) {
			return -1;
		} else if (this.popularity < otherKid.popularity) {
			return 1;
		} else {
			return this.name.compareTo(otherKid.getName());
		}
	}

}
