package kmerZh;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class kmerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	private final IntWritable kmerValue = new IntWritable(1);
	private Text kmerKey = new Text("");

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		String line = ivalue.toString();
		
		for(int i = 0; i < line.length()-1; i++) {
			String kmers = line.substring(i, i+2);
			if(kmers.contains("C") && !kmers.contains("G")) {
				kmerKey.set(kmers);
				context.write(kmerKey, kmerValue);
			}
		}
		
	}
	
}
