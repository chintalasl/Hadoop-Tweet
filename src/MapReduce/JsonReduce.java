package MapReduce;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import com.google.gson.JsonObject;

public class JsonReduce extends Reducer<Text, FloatWritable, Text, JsonObject> {
	private static final Text dummyText = new Text("");

	@Override
	public void reduce(Text key, Iterable<FloatWritable> values, Context context)
			throws IOException, InterruptedException {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("Word", key.toString());
		float count = 0;
		for (FloatWritable val : values) {
			count = count + val.get();
		}
		jsonObject.addProperty("Number", count);
		FloatWritable c=new FloatWritable(count);
		// Key does not matter.
		context.write(dummyText, jsonObject);
	}
}