package zh;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class zhMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private Text kWord = new Text("");
	private IntWritable one = new IntWritable(1);
	private List<String> items;

	public void setup(Context context) {
		items = new LinkedList<String>();

	}

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		items.add(ivalue.toString());
		
	}

	public void cleanup(Context context) throws IOException, InterruptedException {
		for (int i = 0; i < items.size(); i++) {
			for (int j = i + 1; j < items.get(i).length(); j++) {
				kWord.set(Character.toString(items.get(i).charAt(j))+Character.toString(items.get(i).charAt(j+1)));
				
				context.write(kWord, one);
			}
		}
	}

}
