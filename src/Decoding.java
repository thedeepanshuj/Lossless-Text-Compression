import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

public class Decoding {

	public static void main(String[] args) {
		
		String pathMap = "decodingHashMap.djm";
		
		HashMap<String, Character> decodingMap = Utils.getHashMapFromFile(pathMap);
		
		String path = "file_compressed.djn";
		
//		String fileName = "file_compressed";
		
		byte[] dataToDecode = getEncodedByteArray(path);
		
		BitSet bitData = BitSet.valueOf(dataToDecode);
		
		String decodedString = "";
		String tempString = "";
		for(int i = 0;i<bitData.size();i++) {
			if(tempString==null) {
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
