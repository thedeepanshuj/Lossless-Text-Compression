import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.stream.Stream;

public class Utils {
	
	//FUNTION TO GET STRING FROM THE FILE AT THE GIVEN PATH
	public static String getStringFromPath(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
		{
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return contentBuilder.toString();
	}
	
	//FUNCTION TO GET BITSET FROM GIVEN TEXT USING THE CHARBIT HASHMAP
	public static BitSet getBitSetfromString(String text, HashMap<Character, String> charBitMap) {
		
		String temp = "";
		for(int i=0;i<text.length();i++) {
			char currentChar = text.charAt(i);
			temp += charBitMap.get(currentChar);
		}
		
		BitSet bitset = new BitSet(temp.length());
		for(int i=0;i<temp.length();i++) {
			if(temp.charAt(i)=='1') {
				bitset.set(i);
			}
		}
		return bitset;
	}
	
	
	//CREATE ENCODED OUTPUT FILE
	public static void createOutputFile(String path, BitSet encodedBitString, String filename) {
		byte[] byteArray = encodedBitString.toByteArray();
		
		try {
			FileOutputStream outputStream = new FileOutputStream(filename+"_compressed.djn");
			outputStream.write(byteArray);
			outputStream.close();
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Output file not created");
		}
		
	}
	
	//FUNCTION TO GIVE DECODING HASHMAP FROM ENCODING HASHMAP
	public static HashMap<String, Character> reverseMap(HashMap<Character, String> charBitMap) {
		
		HashMap<String, Character> mapToReturn = new HashMap<>();
		for(char currentChar: charBitMap.keySet()) {
			mapToReturn.put(charBitMap.get(currentChar), currentChar);
		}
		
		return mapToReturn;
	}
}
