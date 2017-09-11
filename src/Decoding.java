import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

public class Decoding {

	public static void main(String[] args) {
		
		HashMap<String, Character> decodingMap = new HashMap<>();
		
		decodingMap.put("0", 'f');
		decodingMap.put("100", 'c');
		decodingMap.put("111", 'e');
		decodingMap.put("101", 'd');
		decodingMap.put("1101", 'b');
		decodingMap.put("11001", 'a');
		decodingMap.put("11000", '\0');
		
		String path = "file_compressed.djn";
		
//		String fileName = "file_compressed";
		
		byte[] dataToDecode = getEncodedByteArray(path);
		
		BitSet bitData = BitSet.valueOf(dataToDecode);
		
		String decodedString = "";
		String tempString = "";
		for(int i = 0;i<bitData.size();i++) {
			if(tempString==null) {
				System.out.println("Sahi pakde");
				return;
			}
			if(decodingMap!=null && decodingMap.containsKey(tempString)) {
				decodedString += decodingMap.get(tempString);
				tempString = "";
			}
			boolean currentBit = bitData.get(i);
			if(currentBit) {
				tempString += "1";
			}
			else {
				tempString += "0";
			}
		}
		
		System.out.println("decoded String - " + decodedString);
		System.out.println("\n\n ***DECODING DONE*** \n\n ");
	}

	private static byte[] getEncodedByteArray(String path) {
		
		try {
			Path filePath = Paths.get(path);
			byte[] data;
			data = Files.readAllBytes(filePath);
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
