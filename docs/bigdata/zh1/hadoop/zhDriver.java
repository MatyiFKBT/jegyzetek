package zh;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class zhDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "JobName");
		job.setJarByClass(zh.zhDriver.class);
		job.setMapperClass(zh.zhMapper.class);

		job.setReducerClass(zh.zhReducer.class);

		// TODO: specify output types
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		FileInputFormat.setInputPaths(job, new Path("zhInput.txt"));
		FileOutputFormat.setOutputPath(job, new Path("zhOut"));
		
		FileSystem fs;
		try {
			fs = FileSystem.get(conf);
			if(fs.exists(new Path("zhOut")))
				fs.delete(new Path("zhOut"), true);
		} catch (IOException el) {
			el.printStackTrace();
		}

		if (!job.waitForCompletion(true))
			return;
	}

}
