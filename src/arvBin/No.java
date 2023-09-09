package arvBin;

public class No {
	private int value;
	private No left;
	private No right;
	
	public No(int value, No left, No right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	public No(int value) {
		this.value = value;
		right = null;
		left = null;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public No getLeft() {
		return left;
	}
	public void setLeft(No left) {
		this.left = left;
	}
	public No getRight() {
		return right;
	}
	public void setRight(No right) {
		this.right = right;
	}
}