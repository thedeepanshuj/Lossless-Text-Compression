
public class MinHeapNode {
	
	private char data;
	private int freq;
	private MinHeapNode left;
	private MinHeapNode right;
	
	public MinHeapNode(char data, int freq) {
		super();
		this.data = data;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}

	public MinHeapNode(char data, int freq, MinHeapNode left, MinHeapNode right) {
		super();
		this.data = data;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	public int getFreq() {
		return freq;
	}

	public char getData() {
		return data;
	}
	
	public MinHeapNode getLeft() {
		return left;
	}

	public void setLeft(MinHeapNode left) {
		this.left = left;
	}

	public MinHeapNode getRight() {
		return right;
	}

	public void setRight(MinHeapNode right) {
		this.right = right;
	}

	
	
	
	
}
