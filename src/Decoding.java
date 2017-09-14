import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.BitSet;
import java.util.HashMap;

public class Decoding {

	public static void main(String[] args) {

		String path = "file.txt_compressed.djn";
		HashMap<String,Object> encodedMap = Utils.getMapFromFilePath(path);
		System.out.println(encodedMap);
		
		@SuppressWarnings("unchecked")
		HashMap<String, Character> decodingMap = (HashMap<String, Character>) encodedMap.get("decodingMap");		
		
		byte[] dataToDecode = (byte[]) encodedMap.get("outputContents");
		BitSet bitData = BitSet.valueOf(dataToDecode);
		System.out.println(bitData);
		
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
		
		//TODO: take input to generate output file or not 
		
		System.out.println("\n ***DECODING DONE*** \n\n ");
	}

	
}
