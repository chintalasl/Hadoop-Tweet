package MapReduce;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;


public class TreeMapSort {

	static TreeMap<Float,String> map=new TreeMap<Float,String>();
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan =new Scanner(new File("output2/part-r-00000"));
		PrintStream print=new PrintStream("Sorted");
		while(scan.hasNext()){
			String line=scan.nextLine();
			String[] words=line.split("\t");
			String word=words[0];
			
			map.put(Float.parseFloat(words[1]),words[0]);
			
		}
		for(Entry<Float,String> e:map.entrySet()){
			print.print("{\"Word\":"+"\""+e.getValue()+"\","+"\"Number\":"+e.getKey()+"}\n");
		}
		print.close();
		scan.close();
	}
}
