import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AssessedCW1 {
	// use inner class and implement Comparable interface
	static class Pair implements Comparable{
		String str;
		int n;
		
		public Pair(String str, int n) {
			this.str = str;
			this.n = n;
		}

		//dynamic polymorphism
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return this.n - ((Pair) o).n;
		}
		
	}
	@SuppressWarnings({"unchecked"})
	 public static void sort(HashMap<String, Integer> input) {
	        ArrayList<Pair> list = new ArrayList<Pair>();
	        for (String id : input.keySet()) {
	            Pair pair = new Pair(id, input.get(id));
	            list.add(pair);
	        }
	        Comparator<Pair> c = new Comparator<Pair>() {

	       		//dynamic polymorphism
				@Override
				public int compare(Pair o1, Pair o2) {
					// TODO Auto-generated method stub
					return o2.n-o1.n;
				}
	        	
	        };
	        Collections.sort(list, c);
	        input.clear();
	        for (Pair p : list) {
	            input.put(p.str, p.n);
	        }
	    }
	
	public static void main(String[] args) {
		String FileName = "/Users/apple/Desktop/Test1.txt";
		String str = "";
		
		ReadFile rf = new ReadFile();
		
		str = rf.read(FileName, str);
		
		String[] strarr = str.split("\\s+");
		String[] lowercaseStr = new String[strarr.length];
		for (int i = 0; i < lowercaseStr.length; i++) {
			lowercaseStr[i] = strarr[i].toLowerCase();
		}
		
		//generic
		List<String> list = new ArrayList<>();
		for(int i=0;i<lowercaseStr.length;i++){
			   if(!list.contains(lowercaseStr[i])){
			    list.add(lowercaseStr[i]);
			   }
		}
		
		//generic
		HashMap<String,Integer> hs = new LinkedHashMap<>();
		for (int i = 0; i < list.size(); i++) {
			int count = 0;
			for (int j = 0; j < lowercaseStr.length; j++) {
				if (lowercaseStr[j].equals(list.get(i))) {
					count++;
				}
			}
			hs.put(list.get(i), count);
		}
		
		sort(hs);
		
		int max = hs.entrySet().iterator().next().getValue();
		
		//generic
		List<String> maxlist = new LinkedList<>();
		Set<Map.Entry<String, Integer>> set = hs.entrySet();
		for (Entry<String, Integer> entry : set) {
			if (entry.getValue().equals(max)) {
				maxlist.add(entry.getKey());
			}
		}
		
		int x = list.indexOf(maxlist.get(0));
		for (String each : maxlist) {
			if (x <= list.indexOf(each)) {
				continue;
			}else
			    x = list.indexOf(each);
		}
		System.out.println(list.get(x) + " " + max);
		//task1 finished
		
		List<String> diffList = new ArrayList<>();
		for (int i = 0; i < strarr.length; i++) {
			if (!diffList.contains(strarr[i])) {
				diffList.add(strarr[i]);
			}
		}
		
		//generic
		HashMap<String,Integer> hs1 = new LinkedHashMap<>();
		for (int i = 0; i < diffList.size(); i++) {
			int count = 0;
			for (int j = 0; j < strarr.length; j++) {
				if (strarr[j].equals(diffList.get(i))) {
					count++;
				}
			}
			hs1.put(diffList.get(i), count);
		}
		
		sort(hs1);
		
		//generic
		List<String> minlist = new LinkedList<>();
		Set<Map.Entry<String, Integer>> set1 = hs1.entrySet();
		for (Entry<String, Integer> entry : set1) {
			if (entry.getValue().equals(1)) {
				minlist.add(entry.getKey());
			}
		}
		
		for(int i = 0; i<minlist.size()-1; i++) {
			System.out.print(minlist.get(i) + " ");
		}
		System.out.print(minlist.get(minlist.size()-1));
		System.out.println();
		//task2 finished
	}
}

class ReadFile{
	public String read(String filename, String allstr) {
		File f = new File(filename);
		FileReader fr = null;
		try {
			fr = new FileReader(f);
				char[] all = new char[(int) f.length()];
				fr.read(all);
				for (int i=0;i<all.length;i++) {
					if ((all[i] >= 0 && all[i] <= 57) || (all[i] >= 58 && all[i] <= 64) || (all[i] >= 91 && all[i] <= 96) || all[i] >=123) {
						all[i] = ' ';
					}
				}
				allstr = new String(all);
			}catch (IOException e) {
				// TODO Auto-generated catch block
				// IOexception handle
				e.printStackTrace();
			}
		finally {
			if (null != fr) {
				try {
					fr.close();
				}catch(IOException e) {
					// TODO Auto-generated catch block
					// IOexception handle
					e.printStackTrace();
				}
			}
		}
		
		return allstr;
	}
}
