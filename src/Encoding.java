import java.util.BitSet;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Encoding {

	public static HashMap<String, Character> decodingHashMap;
	
	public static void main(String[] args) {
		
		//PATH TO THE FILE TO BE COMPRESSED
		String path = "file.txt";
		
		//STRING TO STORE FILENAME FOR THE ENCODED FILE
		String filename = "file";
		
		//TEXT FROM THE FILE IS CONVERTED TO STRING
		String dataToEncode = Utils.getStringFromPath(path);
		
		//ENCODING MAPPING FOR THE STRING IS CREATED TO COMPRESS IT
		HashMap<Character, String> encodingHashMap = encodeString(dataToEncode);
		
		//COMPRESSED STRING FROM THE FUNCTION
		BitSet encodedBitString = Utils.getBitSetfromString(dataToEncode,encodingHashMap);
		
		//HASHMAP USED FOR DECODING TO BE WRITTEN IN OUTPUT FROM CHARBITMAP
		decodingHashMap = Utils.reverseMap(encodingHashMap);
		
		//CREATING OUTPUT FILE FROM ENCODED STRING AND DECODING HASHMAP
		Utils.createOutputFile(path,encodedBitString,filename);
		Utils.hashMapToFile(decodingHashMap);
		System.out.println("***ENCODING FINISHED*** ");
	}

	//FUNCTION TO RETURN HASHMAP TO ENCODE STRING
	private static HashMap<Character, String> encodeString(String text) {
		
		//CREATING A HASHMAP FOR FREQUENCY OF DIFFERENT CHARACTERS IN STRING
		int[] freqArray = new int[256];
		for(int i=0;i<text.length();i++) {
			char currentChar = text.charAt(i);
			freqArray[currentChar]++;
		}
		
		//JUST TO CHECK IF FREQUENCIES ARE CORRECT
		
		System.out.println("***FREQUENCY ARRAY CREATED*** \n");
		
		//CREATED MIN PRIORITY QUEUE
		PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
		
		//ADD ALL HUFFMAN TREE NODES TO PRIORITY QUEUE
		for(int i=0;i<freqArray.length;i++) {
			char currentChar = (char) i;
			if(freqArray[i]==0) {
				continue;
			}
			priorityQueue.add(new HuffmanNode(currentChar, freqArray[i]));
		}
		System.out.println("***PRIORITY QUEUE CREATED*** \n");
		
		//CREATING A HUFFMAN TREE
		HuffmanNode root = null;
		while(priorityQueue.size()>1) {
			HuffmanNode left = priorityQueue.poll();
			HuffmanNode right = priorityQueue.poll();
			int newFreq;
			//System.out.println("priorityQueue "+left.getData()+ " " + left.getFreq());
			//System.out.println("priorityQueue "+right.getData()+ " " + right.getFreq());
			newFreq = left.getFreq()+right.getFreq();
			root = new HuffmanNode('\0',newFreq,left,right);
			priorityQueue.add(root);
		}
			
		System.out.println("***HUFFMAN TREE CREATED***");
			
		//CHARACTER AND STRING MAP FROM THE HUFFMAN TREE
		HashMap<Character,String> charStringMap = getMapfromTree(root,"1"); 
		
		return charStringMap;
	}
	
	//FUNCTION TO GIVE CHARACTER STRING HASHMAP FOR GIVEN HUFFMAN TREE
	private static HashMap<Character, String> getMapfromTree(HuffmanNode root,String currentString) {
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