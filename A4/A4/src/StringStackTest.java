import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringStackTest {

	@Test
	//Test for StringStack of size 0 
	void testEmpty() {
		StringStack s = new StringStack();
		
		assertEquals(0, s.size());
	}	
	@Test
	//Test for correct size from StringStack constructor
	void testSize() {
		StringStack s = new StringStack(10);
		
		assertEquals(10, s.size());
	}
	@Test
	//Test for string to be correctly pushed to first index
	void testPush() {
		String str = "string 1";
		StringStack s = createStringStack(1, str);
				
		assertEquals(str, s.array[0]);
	}
	@Test
	//Test for strings to be pushed to correct indicies 
	void testPush2() {
		String str1 = "string 1";
		String str2 = "string 2";
		StringStack s = createStringStack(10, str1);
		s.push(str2);
		
		assertEquals(str2, s.array[1]);
	}
	@Test
	//Test for return of string from pop 
	void testPop() {
		String str = "string to pop";
		StringStack s = createStringStack(1, str);
		
		String returnStr = s.pop();
		
		assertEquals(str, returnStr);
	}
	@Test
	//test for first string to be returned after pushing string on top of StringStack after string 1
	void testPop2() {
		String str1 = "string to pop";
		String str2 = "string to pop 2";
		StringStack s = createStringStack(2, str1);
		
		//push second string and then pop second string
		s.push(str2);
		s.pop();
		
		String returnStr = s.pop();
		
		assertEquals(str1, returnStr);
	}
	//Helper method for creating and pushing to StringStack
	StringStack createStringStack(int size, String str) {
		StringStack s = new StringStack(size);
		s.push(str);
		
		return s;
	}

}
