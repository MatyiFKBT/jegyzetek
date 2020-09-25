package kindergarten.view;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class GiziNeni implements Gizi {

	@Override
	public String prepareData(InputStream inputStream) throws IOException {
		try (Reader reader = new InputStreamReader(inputStream)) {
			StringBuilder sb = new StringBuilder();
			while (reader.ready()) {
				sb.append((char) reader.read());
			}
			return sb.toString();
		}
	}

}
