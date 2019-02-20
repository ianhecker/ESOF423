package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

/**
 * This is just a simple template for a JUnit test-class for testing 
 * the class Customer.
 */
public class Customer_test {

	/*
	@Test
	public void test1() {
		System.out.println("Provide here a short description of your test purpose here...") ;
		Customer C = new Customer(0,"Duffy Duck","") ;
		assertTrue(C.getCostToPay() == 0) ; 
	}
	
	@Test
	public void test2() { 
		System.out.println("Provide a short description...") ;
		// an example of test that fails, in this case trivially:
		assertTrue(1/0 == 0) ;
	}
	
	// and so on ...
	 */
	
	//Tests for the return value of zero for no customer participations
	@Test
	public void participationValue() {
		System.out.println("** Testing Class: Customer | Method: participationValue");
		Customer c = new Customer(1, "test 1", "");
		assertTrue(c.participationValue() == 0);
	}
	
	//Tests for the return value of zero for no customer discounts
	@Test
	public void getDiscountValue() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue");
		Customer c = new Customer(2, "test 2", "");
		assertTrue(c.getDiscountValue() == 0);
	}
	
	//Tests for the return value of zero for no customer costs
	@Test
	public void getCostToPay() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay");
		Customer c = new Customer(3, "test 3", "");
		assertTrue(c.getCostToPay() == 0);
	}
	
	//Tests for the return value of zero for no customer participation groups
	@Test
	public void getParticipationGroups() {
		System.out.println("** Testing Class: Customer | Method: getParticipationGroups");
		Customer c = new Customer(4, "test 4", "");
		assertTrue(c.getParticipationGroups() == null);
	}
	
	
	
}
