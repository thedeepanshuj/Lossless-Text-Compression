import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;

public class HuffmanDecoding {

	public static void main(String[] args) {
		
		HashMap<BitSet,Character> decodingMap = HuffmanEncoding.decodingHashMap;
		
		String path = "file_compressed.dji";
		
		String fileName = "file_compressed";
		
		byte[] dataToDecode = getEncodedByteArray(path);
		
		BitSet bitData = BitSet.valueOf(dataToDecode);
		
		String encodedString = "";
		for(int i = 0;i<bitData.size();i++) {
			boolean currentBit = bitData.get(i);
			if(currentBit) {
				encodedString += "1";
			}
			else {
				encodedString += "0";
			}
		}
		
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
