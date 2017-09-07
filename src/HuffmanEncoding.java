import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanEncoding {



	//MAIN FUNCTION
	public static void main(String[] args) {
		
		String sampleString = "fdfdfdfdfdfdfdfdfdfdfdfdfdfcfcfcfcfcfcfcfcfcfcfcfceeeeeeeeeee"
				+ "fafafafafafbfbfbfbfbfbfbfbfbfefefefefef";
		
		//PATH TO THE FILE TO BE COMPRESSED
		String path = "";
		
		//TEXT FROM THE FILE IS CONVERTED TO STRING
		String dataToEncode = getStringFromPath(path);
		
		//ENCODING MAPPING FOR THE STRING IS CREATED TO COMPRESS IT
		HashMap<Character,BitSet> charBitMap = encodeString(dataToEncode);
		
		//COMPRESSED STRING FROM THE FUNCTION
		BitSet encodedBitString = getBitSetfromString(dataToEncode,charBitMap);
		
		//TODO CREATE A FILE USING THE ENCODED-STRING AND HASHMAP
	}

	//FUNTION TO GET STRING FROM THE FILE AT THE GIVEN PATH
	private static String getStringFromPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	//FUNCTION TO GET BITSET FROM GIVEN TEXT USING THE CHARBIT HASHMAP
	private static BitSet getBitSetfromString(String text, HashMap<Character, BitSet> charBitMap) {
		
		String temp = "";
		for(int i=0;i<text.length();i++) {
			char currentChar = text.charAt(i);
			temp += charBitMap.get(currentChar).toString();
		}
		
		BitSet bitset = new BitSet(temp.length());
		for(int i=0;i<temp.length();i++) {
			if(temp.charAt(i)=='1') {
				bitset.set(i);
			}
		}
		return bitset;
	}

	//FUNCTION TO RETURN HASHMAP TO ENCODE STRING
	private static HashMap<Character,BitSet> encodeString(String text) {
		
		HashMap<Character, Integer> freqMap = new HashMap<>();
		
		for(int i=0;i<text.length();i++) {
			char currentChar = text.charAt(i);
			freqMap.putIfAbsent(currentChar, 0);
			int oldFreq = freqMap.get(currentChar);
			oldFreq++;
			freqMap.put(currentChar, oldFreq);
		}
		
		//JUST TO CHECK IF FREQUENCIES ARE CORRECT
		for(char currentChar: freqMap.keySet()) {
			System.out.println("freqMap "+currentChar+ " " + freqMap.get(currentChar));
		}
			
		//CREATED PRIORITY QUEUE
		PriorityQueue<MinHeapNode> priorityQueue = new PriorityQueue<>(new Comparator<MinHeapNode>() {
			public int compare(MinHeapNode lhs, MinHeapNode rhs) {
				if(lhs.getFreq() > rhs.getFreq())	return 1;
				if(lhs.getFreq() < rhs.getFreq()) 	return -1;
				return 0;
			}
		});
			
			
		//ADD ALL HUFFMAN TREE NODES TO PRIORITY QUEUE
		for(char currentChar: freqMap.keySet()) {
			priorityQueue.add(new MinHeapNode(currentChar, freqMap.get(currentChar)));
		}
			
			
		//CREATING A HUFFMAN TREE
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
			
		//CHARACTER AND STRING MAP FROM THE HUFFMAN TREE
		HashMap<Character,String> charStringMap = getMapfromTree(root,""); 
		
		//CHARACTER AND STRING HASHMAP TO CHARACTER AND BITSET HASHMAP
		HashMap<Character, BitSet> charBitMap = new HashMap<>();
		for(char currentChar: charStringMap.keySet()) {
			String bitString = charStringMap.get(currentChar);
			BitSet bitSet = getBitsetFromString(bitString); 
			charBitMap.put(currentChar, bitSet);
		}
		
		System.out.println("charBit map created " + charBitMap.size());
		return charBitMap;
	}

		
	//FUNCTION TO CONVERT GIVEN STRING TO BITSET
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

	//FUNCTION TO GIVE CHARACTER STRING HASHMAP FOR GIVEN HUFFMAN TREE
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
