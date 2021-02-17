package zh;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class zhReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private IntWritable sum = new IntWritable(0);
	
	
	
	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		// process values
		String st = _key.toString();
		int s = 0;
		for (IntWritable val : values) {
			s += val.get();
		}
		if(st.contains("C")&&!st.contains("G")&&s>6) {
			context.write(_key, sum);
		}
		
	}

}
