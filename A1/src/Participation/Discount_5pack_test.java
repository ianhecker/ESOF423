package Participation;
import org.junit.* ;
import java.util.*;
import static org.junit.Assert.* ;

public class Discount_5pack_test {

	//Expecting return of false for applicable() because customer does not have 5 services
	@Test
	public void Applicable1() {
		System.out.println("** Testing Class: Discount_5pack | Method: Applicable | Against: Only one Participation but over 100 minimum");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 100);
		Participation p0 = new Participation(c0, s0);
		Discount_5pack d0 = new Discount_5pack();
		
		c0.participations.add(p0);		
		assertTrue(d0.applicable(c0) == false);
	}

	//Expecting return of true for applicable() because customer does have 5 services over 100 each
	@Test
	public void Applicable2() {
		System.out.println("** Testing Class: Discount_5pack | Method: Applicable | Against: 5 Participations each over 100 euro minimum");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 100);
		Service s1 = new Service(2, "Dropping a Cours", 100);
		Service s2 = new Service(3, "Dropping a Cour", 100);
		Service s3 = new Service(4, "Dropping a Cou", 100);
		Service s4 = new Service(5, "Dropping a Co", 100);
		Participation p0 = new Participation(c0, s0);
		Participation p1 = new Participation(c0, s1);
		Participation p2 = new Participation(c0, s2);
		Participation p3 = new Participation(c0, s3);
		Participation p4 = new Participation(c0, s4);
		Discount_5pack d0 = new Discount_5pack();
		
		c0.participations.add(p0);
		c0.participations.add(p1);
		c0.participations.add(p2);
		c0.participations.add(p3);
		c0.participations.add(p4);	
		assertTrue(d0.applicable(c0) == true);
	}

	//Expecting return of false for applicable() because customer does not have 5 services over 100 each
	@Test
	public void Applicable3() {
		System.out.println("** Testing Class: Discount_5pack | Method: Applicable | Against: 5 Participations not over 100 minimum euro");
		Customer c0 = new Customer(1, "Ben Bushnell", "dropping_esof423@student.edu");
		Service s0 = new Service(1, "Dropping a Course", 1);
		Service s1 = new Service(2, "Dropping a Cours", 1);
		Service s2 = new Service(3, "Dropping a Cour", 1);
		Service s3 = new Service(4, "Dropping a Cou", 1);
		Service s4 = new Service(5, "Dropping a Co", 1);
		Participation p0 = new Participation(c0, s0);
		Participation p1 = new Participation(c0, s1);
		Participation p2 = new Participation(c0, s2);
		Participation p3 = new Participation(c0, s3);
		Participation p4 = new Participation(c0, s4);
		Discount_5pack d0 = new Discount_5pack();
		
		c0.participations.add(p0);
		c0.participations.add(p1);
		c0.participations.add(p2);
		c0.participations.add(p3);
		c0.participations.add(p4);	
		assertTrue(d0.applicable(c0) == false);
	}

	//Expecting return of 10 for calcDiscount()
	@Test
	public void CalcDiscount1() {
		System.out.println("** Testing Class: Discount_5pack | Method: calcDiscount | Against: return of 10");
		Customer c0 = new Customer(1, "Grace Walkuski", "icoachswimming@yippee.com");
		Discount d0 = new Discount_5pack(); 
				
		assertTrue(d0.calcDiscount(c0) == 10);
	}
}
