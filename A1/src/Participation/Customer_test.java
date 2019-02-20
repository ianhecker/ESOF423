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
	public void pv1() {
		System.out.println("** Testing Class: Customer | Method: participationValue | Against: No Participations");
		Customer c = new Customer(1, "test 1.0", "");
		assertTrue(c.participationValue() == 0);
	}
	
	//Tests for the return price of 100 for one participation in "Pizza" service
	@Test
	public void pv2() {
		System.out.println("** Testing Class: Customer | Method: participationValue | Against: Return of one Participation Service price");		
		
		Customer c = new Customer(1, "test 1.1", "");
		Service s = new Service(1, "Pizza", 100);
		Participation p = new Participation(c, s);
		
		c.participations.add(p);
		assertTrue(c.participationValue() == 100);
	}
	//Tests for the return price of 1000 for five participations in services
		@Test
		public void pv3() {
			System.out.println("** Testing Class: Customer | Method: participationValue | Against: Sum of 5 Participation Service prices");		
			
			Customer c = new Customer(1, "test 1.2", "");
			Service s = new Service(1, "Pizza", 100);
			Participation p1 = new Participation(c, s);
			Participation p2 = new Participation(c, s);
			Participation p3 = new Participation(c, s);
			Participation p4 = new Participation(c, s);
			Participation p5 = new Participation(c, s);
			c.participations.add(p1);
			c.participations.add(p2);
			c.participations.add(p3);
			c.participations.add(p4);
			c.participations.add(p5);
			assertTrue(c.participationValue() == 500);
		}	
	//-----------------------------------------------------------------------------------
	//Tests for the return value of zero for no customer discounts
	@Test
	public void gdv1() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue | Against: No Customer Discounts");
		Customer c = new Customer(2, "test 2", "");
		assertTrue(c.getDiscountValue() == 0);
	}
	//-----------------------------------------------------------------------------------
	//Tests for the return value of zero for no customer costs
	@Test
	public void gctp1() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay");
		Customer c = new Customer(3, "test 3", "");
		assertTrue(c.getCostToPay() == 0);
	}
	//-----------------------------------------------------------------------------------
	//Tests for the return value of zero for no customer participation groups
	@Test
	public void gpg1() {
		System.out.println("** Testing Class: Customer | Method: getParticipationGroups");
		Customer c = new Customer(4, "test 4", "");		
		assertTrue(c.getParticipationGroups() == null);
	}
}
