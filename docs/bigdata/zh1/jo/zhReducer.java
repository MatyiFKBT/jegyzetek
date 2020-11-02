package kmerZh;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class kmerReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text _key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
		
		IntWritable kmersValuesSum = new IntWritable(1);
		int sum = 0;
		// process values
		for (IntWritable val : values) {
			sum += val.get();
		}
		
		if(sum >= 6) {
			kmersValuesSum.set(sum);
			context.write(_key, kmersValuesSum);
		}
	}

}
