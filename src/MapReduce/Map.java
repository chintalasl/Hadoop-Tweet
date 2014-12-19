package MapReduce;
/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map extends Mapper<Object, Text, Text, FloatWritable> {
	public static void populateSentimentMap() throws FileNotFoundException {

		Scanner scan = new Scanner(new File("unigrams-pmilexicon.txt"));
		while (scan.hasNext()) {
			String line = scan.nextLine();
			String[] words = line.split("\t");
			if (words.length < 2)
				continue;
			String word = words[0], word2;
			word2 = word.replaceAll("[^a-zA-Z ]", " ").toLowerCase();

			if (!word.equals(word2)) {
				System.out.println("continuing " + word);
				continue;
			}
			float val = Float.parseFloat(words[1]);
			map.put(word, val);

		}
		scan.close();
	}

	private final static FloatWritable one = new FloatWritable(1);
	private Text word = new Text();
	public static HashMap<String, Float> map = new HashMap<String, Float>();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] split = line.split(",");
		StringTokenizer itr = new StringTokenizer(split[1]);
		Float tot = (float) 0;

		while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
			String wordt = word.toString();
			Float val = map.get(wordt);
			if (val != null)
				tot += val;

		}
		Text date = new Text();
		date.set(split[0]);
		one.set(tot);
		context.write(date, one);
	}

}