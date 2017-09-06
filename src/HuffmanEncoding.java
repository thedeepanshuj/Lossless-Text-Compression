import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanEncoding {


//"fdfdfdfdfdfdfdfdfdfdfdfdfdfcfcfcfcfcfcfcfcfcfcfcfceeeeeeeeeeefafafafafafbfbfbfbfbfbfbfbfbfefefefefef"
	public static void main(String[] args) {
		
		String path = "";
		String dataToEncode = getStringFromPath(path);
		HashMap<Character,BitSet> charBitMap = encodeString(dataToEncode);
		
	}

	private static String getStringFromPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static HashMap<Character,BitSet> encodeString(String text) {
		
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
			System.out.println("freqMap "+currentChar+ " " + freqMap.get(currentChar));
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
		MinHeapNode root = null;
		while(priorityQueue.size()>1) {
			MinHeapNode left = priorityQueue.poll();
			MinHeapNode right = priorityQueue.poll();
			int newFreq;
			System.out.println("priorityQueue "+left.getData()+ " " + left.getFreq());
			System.out.println("priorityQueue "+right.getData()+ " " + right.getFreq());
			newFreq = left.getFreq()+right.getFreq();
			
			root = new MinHeapNode('\0',newFreq,left,right);
			priorityQueue.add(root);
		}
		
		System.out.println("huffman tree created");
		
		//get bytes string and character map from the huffman tree
		HashMap<Character,String> charStringMap = getMapfromTree(root,""); 
		HashMap<Character, BitSet> charBitMap = new HashMap<>();
		
		for(char currentChar: charStringMap.keySet()) {
			String bitString = charStringMap.get(charStringMap);
			BitSet bitSet = getBitsetFromString(bitString); 
			charBitMap.put(currentChar, bitSet);
		}
		
		System.out.println("charBit map created " + charBitMap.size());
		return charBitMap;
		
	}

	private static BitSet getBitsetFromString(String bitString) {
		if(bitString == null) {
			return null;
		}
		BitSet bitset = new BitSet(bitString.length());
		for(int i=0;i<bitString.length();i++) {
			if(bitString.charAt(i)=='1') {
				bitset.set(i);
			}
		}
		return bitset;
	}

	private static HashMap<Character, String> getMapfromTree(MinHeapNode root,String currentString) {
		if(root == null) {
			return null;
		}
		
		if(root.getLeft()== null && root.getRight() == null) {
			HashMap<Character,String> newMap = new HashMap<>();
			newMap.put(root.getData(), currentString);
			return newMap;
		}
		
		HashMap<Character,String> leftMap = getMapfromTree(root.getLeft(), currentString+"0");
		HashMap<Character,String> rightMap = getMapfromTree(root.getRight(), currentString+"1");
		
		HashMap<Character,String> ans = new HashMap<>();
		
		ans.putAll(leftMap);
		ans.putAll(rightMap);
		
		return ans;
	}
	
}
