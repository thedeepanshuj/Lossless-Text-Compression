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
				if(lhs.getFreq() > rhs.getFreq()) {
					
					return 1;
					
				}
				if(lhs.getFreq() < rhs.getFreq()) {
					return -1;
				}
				return 0;
			}
		});
		
		//add all to the priority queue
		Iterator it = freqMap.entrySet().iterator();
		while(it.hasNext()) {
			@SuppressWarnings("unchecked")
			Map.Entry<Character, Integer> pair = (Entry<Character, Integer>) it.next();
			priorityQueue.add(new MinHeapNode(pair.getKey(), pair.getValue()));
			it.remove();
		}
		
		while(!priorityQueue.isEmpty()) {
			MinHeapNode heapNode = priorityQueue.poll();
			System.out.println("Queue"+heapNode.getData()+" "+heapNode.getFreq());
		}
		
		//pop each value and add to the huffman tree
		
		
	}

}
