import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanEncoding {

	
	
	public static void main(String[] args) {
		
		String path = "hajhfjahjadjashdasjhdbasjdabdjhabdjashdbasjdbasdasjdbasdjhahdkjahdkjasdhkasdhaskjda";
		//String dataToEncode = getStringFromPath(path);
		encodeString(path);
		
	}

	private static String getStringFromPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static void encodeString(String text) {
		
		char[] textArray = text.toCharArray();
		HashMap<Character, Integer> freqMap = new HashMap<>();
		
		for(int i=0;i<textArray.length;i++) {
			char currentChar = textArray[i];
			
			freqMap.putIfAbsent(currentChar, 0);
			int oldFreq = freqMap.get(currentChar);
			oldFreq++;
			freqMap.put(currentChar, oldFreq);
		}
		
		for(char currentChar: freqMap.keySet()) {
			System.out.println("map"+currentChar+ " " + freqMap.get(currentChar));
		}
		
		//create priority queue
		PriorityQueue<MinHeapNode> priorityQueue = new PriorityQueue<>(new Comparator<MinHeapNode>() {
			public int compare(MinHeapNode lhs, MinHeapNode rhs) {
				if(lhs.getFreq() > rhs.getFreq())	return 1;
				if(lhs.getFreq() < rhs.getFreq()) 	return -1;
				return 0;
			}
		});
		
		
		//add all to the priority queue
		for(char currentChar: freqMap.keySet()) {
			priorityQueue.add(new MinHeapNode(currentChar, freqMap.get(currentChar)));
		}
		
		
		//pop all from priority queue and add to huffman tree
		MinHeapNode root;
		while(!priorityQueue.isEmpty()) {
			MinHeapNode left = priorityQueue.poll();
			MinHeapNode right = priorityQueue.poll();
			int newFreq = left.getFreq()+right.getFreq();
			root = new MinHeapNode('\0',newFreq,left,right);
			priorityQueue.add(root);
		}
		
		//get bitString and cha
		
	}

}
