
public class StringStack {

	public String[] array; //holds strings
	private int top; //index above top item
	
	//Create empty stack	
	public StringStack () {
		this.array = new String[0];
		this.top = 0;
	}
	//Create stack of specified size
	public StringStack (int size) {
		this.array = new String[size];
		this.top = 0;
	}
	//check stack size
	public int size() {
		return array.length;
	}
	//Push string to stack, return boolean of success
	public boolean push(String str) {
		if ( top < size() ) {
			array[top] = str;
			top++;
			return true;
		}
		return false;
	}
	//Pop string from stack
	public String pop() {
		if ( size() != 0 ) {
	 		String temp = array[top - 1];
			top--;
			return temp;
		}
		return null;
	}
	
	
}
