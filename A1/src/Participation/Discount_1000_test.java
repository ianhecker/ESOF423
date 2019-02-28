package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;



public class Discount_1000_test {

	//Expecting return of false for applicable() because customer does have services over 1000
	@Test
	public void Applicable1() {
		System.out.println("** Testing Class: Discount_1000 | Method: Applicable | Against: Only one customer participation");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 100);		
		Participation p0 = new Participation(c0, s0);
		Discount_1000 d0 = new Discount_1000();
		
		c0.participations.add(p0);
		boolean getDiscount = d0.applicable(c0);
		assertTrue(getDiscount == false);
	}
	
	//Expecting return of true for applicable() because customer does have services over 100000 total cost
	@Test
	public void Applicable2() {
		System.out.println("** Testing Class: Discount_5pack | Method: Applicable | Against: 5 Participations not over 100 minimum euro");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 20000);
		Service s1 = new Service(2, "Dropping a Cours", 20000);
		Service s2 = new Service(3, "Dropping a Cour", 20000);
		Service s3 = new Service(4, "Dropping a Cou", 20000);
		Service s4 = new Service(5, "Dropping a Co", 20000);
		Participation p0 = new Participation(c0, s0);
		Participation p1 = new Participation(c0, s1);
		Participation p2 = new Participation(c0, s2);
		Participation p3 = new Participation(c0, s3);
		Participation p4 = new Participation(c0, s4);
		Discount_1000 d0 = new Discount_1000();
		
		c0.participations.add(p0);
		c0.participations.add(p1);
		c0.participations.add(p2);
		c0.participations.add(p3);
		c0.participations.add(p4);
		
		assertTrue(d0.applicable(c0) == true);
	}
	
	//Expecting return of true for applicable() because customer does have services over 1000
	@Test
	public void Applicable3() {
		System.out.println("** Testing Class: Discount_5pack | Method: Applicable | Against: Participations not over 100000 euro");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 99999);
		Participation p0 = new Participation(c0, s0);
		Discount_1000 d0 = new Discount_1000();
		
		c0.participations.add(p0);
		assertTrue(d0.applicable(c0) == true);
	}

	@Test
	public void calcDiscount1() {
		System.out.println("** Testing Class: Discount_1000 | Method: calcDiscount");
		Customer c0 = new Customer(1, "Lil Wayne", "");
		Service s0 = new Service(1, "Socks on in the jacuzzi", 150000);
		Participation p0 = new Participation(c0, s0);
		c0.participations.add(p0);
		
		Discount_1000 d0 = new Discount_1000();		
		assertTrue(d0.calcDiscount(c0) == 5000);
	}
	
	@Test
	public void calcDiscount2() {
		System.out.println("** Testing Class: Discount_1000 | Method: calcDiscount");
		Customer c0 = new Customer(1, "Poor Boy", "");
		Service s0 = new Service(1, "Bread and Water", 1);
		Participation p0 = new Participation(c0, s0);
		c0.participations.add(p0);
		
		Discount_1000 d0 = new Discount_1000();		
		assertTrue(d0.calcDiscount(c0) == 0);
	}

}
