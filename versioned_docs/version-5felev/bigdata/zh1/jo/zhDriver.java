package kmerZh;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class kmerDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		job.setJarByClass(kmerZh.kmerDriver.class);
		job.setMapperClass(kmerZh.kmerMapper.class);

		job.setReducerClass(kmerZh.kmerReducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("kmerInput.txt"));
		FileOutputFormat.setOutputPath(job, new Path("kmerOut"));
		
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			if(fs.exists(new Path("kmerOut")))
				fs.delete(new Path("kmerOut"), true);
		} catch (IOException el) {
			el.printStackTrace();
		}

		if (!job.waitForCompletion(true))
			return;
	}

}
