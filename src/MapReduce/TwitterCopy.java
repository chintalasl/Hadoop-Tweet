package MapReduce;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.Map.Entry;

public class TwitterCopy {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("entertainment"));// /home/sowmya/Downloads/Assignment3Tweets-2"));
		PrintStream entert = null;

		entert = new PrintStream(new FileOutputStream("news.json"));
		String[] positive = { "love", "favorite", "amazing", "good", "gracias",
				"beautiful", "perfect", "congrats", "awesome", "happy",
				"thank", "nice", "excited", "greatest", "incredible" };
		String[] neg = { "shit", "scary", "problem", "yuk", "killed",
				"terrible", "horror", "abandon", "hell", "annoying", "sucks",
				"sick", "ridiculous", "ruin", "waste" };

		HashMap<String, String> pmap = new HashMap<String, String>();
		HashMap<String, String> nmap = new HashMap<String, String>();
		String[] month = { "Jan 2014", "May 2014", "July 2014 ", "Sept 2014",
				"Dec 2014" };

		while (scan.hasNext()) {
			String line = scan.nextLine();
			boolean done = false;
			if (pmap.size() == positive.length && nmap.size() == neg.length)
				break;
			if (line.contains("news") || line.contains("News")) {
				for (int i = 0; i < positive.length; i++) {
					if (pmap.containsKey(positive[i]))
						continue;
					if (line.contains(positive[i])) {
						pmap.put(positive[i], line);
						done = true;
					}
				}
				if (done == true)
					continue;
				for (int i = 0; i < neg.length; i++) {
					if (nmap.containsKey(neg[i]))
						continue;
					if (line.contains(neg[i])) {
						nmap.put(neg[i], line);
						done = true;
					}
				}

			}

		}

		Random r = new Random();
		entert.print("{\"Positive\":{\n");
		for (Entry<String, String> e : pmap.entrySet()) {
			entert.print("{\"Word\":" + e.getKey() + ",\"News\":"
					+ e.getValue() + "}");
		}
		entert.print("}}");
		entert.print("{\"Negative\":{\n");
		for (Entry<String, String> e : nmap.entrySet()) {
			entert.print("{\"Word\":" + e.getKey() + ",\"News\":"
					+ e.getValue() + "}\n");
		}
		entert.print("}}");
		/*
		 * for(long i=1;i<=1000000000;i++){ String line =scan.nextLine();
		 * //print.print(line); for(String s:topics){ if(line.contains(s)){
		 * entert.print(line+"\n"); break;} } //print.print("\n"); } for(long
		 * i=1;i<=10000;i++){ String line =scan.nextLine(); //print.print(line);
		 * for(String s:topics){ if(line.contains(s)){ entert.print(line+"\n");
		 * break;} } //print.print("\n"); }
		 */
		scan.close();

		entert.close();

	}
}
