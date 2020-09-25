package kindergarten.control;

import java.io.IOException;
import java.io.InputStream;

import kindergarten.model.Kid;
import kindergarten.model.KinderGarten;
import kindergarten.view.Gizi;
import kindergarten.view.GiziNeni;

public class KinderGartenCalculator {

	private Gizi gizi = new GiziNeni();
	private Processor proc = new DataProcessor();

	public String run(InputStream input) throws IOException, UnnamedKidException {
		String data = gizi.prepareData(input);
		KinderGarten kinderGarten = proc.process(data);
		Kid mostPopularKid = kinderGarten.getMostPopularKid();
		return mostPopularKid.toString();
	}

}
