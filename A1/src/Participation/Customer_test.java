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
		Customer c = new Customer(2, "test 2.0", "");
		assertTrue(c.getDiscountValue() == 0);
	}
	
	//Expecting return value of 0 for Discount value, for no participations 
	@Test
	public void gdv2() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue | Against: One Customer Discount 5 Pack w/ no Participations");
		Customer c = new Customer(2, "test 2.1", "");
		Discount d = new Discount_5pack();
		
		c.discounts.add(d);		
		assertTrue(c.getDiscountValue() == 0);
	}
	
	//Expecting return value of 0 for Discount value, for < 5 participations
	@Test
	public void gdv3() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue | Against: One Customer Discount 5 Pack w/ 1 Participations");
		Customer c = new Customer(2, "test 2.2", "");
		Discount d = new Discount_5pack();
		Service s = new Service(2, "Linguini", 100);
		Participation p = new Participation(c, s);
		
		c.discounts.add(d);
		c.participations.add(p);
		assertTrue(c.getDiscountValue() == 0);
	}
	
	//Expecting return value of 10 (Discount_5Pack gives 10 Euro discount for >= 5 services)
	@Test
	public void gdv4() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue | Against: One Customer Discount 5 Pack w/ 5 Participations");
		Customer c = new Customer(2, "test 2.3", "");
		Discount d = new Discount_5pack();
		Service s = new Service(2, "Linguini", 100);
		Participation p = new Participation(c, s);
		
		c.discounts.add(d);
		c.participations.add(p);
		c.participations.add(p);
		c.participations.add(p);
		c.participations.add(p);
		c.participations.add(p);		
		assertTrue(c.getDiscountValue() == 10);
	}
	
	//Expecting return value of 20 (2 Discount_5Packs gives 2*(10) Euro discount for >= 5 services)
	@Test
	public void gdv5() {
		System.out.println("** Testing Class: Customer | Method: getDiscountValue | Against: One Customer 2 Discount 5 Packs w/ 5 Participations");
		Customer c = new Customer(2, "test 2.3", "");
		Discount d = new Discount_5pack();
		Service s0 = new Service(0, "A", 200);
		Service s1 = new Service(1, "B", 200);
		Service s2 = new Service(2, "C", 200);
		Service s3 = new Service(3, "D", 200);
		Service s4 = new Service(4, "E", 200);
		Participation p0 = new Participation(c, s0);
		Participation p1 = new Participation(c, s1);
		Participation p2 = new Participation(c, s2);
		Participation p3 = new Participation(c, s3);
		Participation p4 = new Participation(c, s4);
		
		c.discounts.add(d);
		c.discounts.add(d);			
		c.participations.add(p0);
		c.participations.add(p1);
		c.participations.add(p2);
		c.participations.add(p3);
		c.participations.add(p4);
		System.out.println(c.getDiscountValue());
		assertTrue(c.getDiscountValue() == 2*10);
	}
	//-----------------------------------------------------------------------------------
	//Expecting the return value of zero for no Customer costs and no Discounts
	@Test
	public void gctp1() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay | Against: Customer w/ no Participations or Discounts");
		Customer c = new Customer(3, "test 3.0", "");
		assertTrue(c.getCostToPay() == 0);
	}
	
	//Expecting the return value of 200 for 2 Customer Participations and no Discounts
	@Test
	public void gctp2() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay | Against: Customer w/ 2 Participations and no Discounts");
		Customer c = new Customer(3, "test 3.1", "");
		Service s = new Service(3, "Maid for ya Dirty House", 100);		
		Participation p = new Participation(c, s);
		
		c.participations.add(p);
		c.participations.add(p);
		assertTrue(c.getCostToPay() == 100);
	}
	
	//Expecting the return value of 200 for 2 Customer Participations and non applicable Discount_5pack (s < 5)
	@Test
	public void gctp3() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay | Against: Customer w/ 2 Participations and non-applicable Discount_5Pack");
		Customer c = new Customer(3, "test 3.2", "");
		Service s0 = new Service(0, "Maid for ya Dirty House", 100);
		Service s1 = new Service(1, "Pizza", 100);
		Participation p0 = new Participation(c, s0);
		Participation p1 = new Participation(c, s1);
		Discount d = new Discount_5pack();
		
		c.discounts.add(d);
		c.participations.add(p0);
		c.participations.add(p1);
		assertTrue(c.getCostToPay() == 2*100);
	}
	
	//Expecting the return of 1000-10 = 990 euros for cost to pay for 5 Participations and an applicable Discount_5Pack 
	@Test
	public void gctp4() {
		System.out.println("** Testing Class: Customer | Method: getCostToPay | Against: Customer w/ 5 Participations and applicable Discount_5Pack");
		Customer c = new Customer(3, "test 3.3", "");
		Service s0 = new Service(0, "Maid for ya Dirty House", 200);
		Service s1 = new Service(1, "Pizza", 200);
		Service s2 = new Service(2, "Zamboni", 200);
		Service s3 = new Service(3, "Milkmaid", 200);
		Service s4 = new Service(4, "Godzilla", 200);
		Participation p0 = new Participation(c, s0);
		Participation p1 = new Participation(c, s1);
		Participation p2 = new Participation(c, s2);
		Participation p3 = new Participation(c, s3);
		Participation p4 = new Participation(c, s4);
		Discount d = new Discount_5pack();
		
		c.discounts.add(d);
		c.participations.add(p0);
		c.participations.add(p1);
		c.participations.add(p2);
		c.participations.add(p3);
		c.participations.add(p4);		
		assertTrue(c.getCostToPay() == 200*5-10);
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
