package kindergarten.control;

import kindergarten.model.Kid;
import kindergarten.model.KinderGarten;

public class DataProcessor implements Processor {
	
	public KinderGarten process(String data) throws UnnamedKidException {
		KinderGarten kinderGarten = new KinderGarten();
		String[] lines = data.split("\r\n");
		for (String line : lines) {
			String[] kids = line.split(" ");
			int n = kids.length;
			Kid first = kinderGarten.getKid(kids[0]);
			kinderGarten.add(first);
			for (int i = 1; i < n; i++) {
				String name = kids[i];
				Kid kid = kinderGarten.getKid(name);
				kinderGarten.add(kid);
				first.like(kid);
			}
		}
		calculatePopularity(kinderGarten);
		return kinderGarten;
	}

	private void calculatePopularity(KinderGarten kg) {
		for (Kid kid : kg.getKids()) {
			for (Kid k : kid.getLikedKids()) {
				incrasePopularity(kg, k.getName());
			}
		}
	}

	private void incrasePopularity(KinderGarten kg, String name) {
		for (Kid k : kg.getKids()) {
			if (k.getName().equals(name)) {
				k.incrasePopularity();
			}
		}
	}

}
