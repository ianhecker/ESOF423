package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;



public class Discount_1000_test {

	//Test Discount_1000 method applicable() 
	@Test
	public void applicable() {
		System.out.println("** Testing Class: Discount_1000 | Method: applicable");
		Customer c = new Customer(1, "test 1", "");
		Discount_1000 d = new Discount_1000();
		assertTrue(d.applicable(c) == false);
	}

	@Test
	public void calcDiscount() {
		System.out.println("** Testing Class: Discount_1000 | Method: calcDiscount");
	}

}
