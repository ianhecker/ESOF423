package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

import java.util.*;

/**
 * This is just a simple template for Junit test-class for testing
 * the class ApplicationLogic. Testing this class is a bit more
 * complicated. It uses database, which form an implicit part of
 * the state of ApplicationLogic. After each test case, you need to
 * reset the content of the database to the value it had, before
 * the test case. Otherwise, multiple test cases will interfere
 * with each other, which makes debugging hard should you find error.
 * 
 */
public class ApplicationLogic_test {

	/**
	 * Provide a functionality to reset the database. Here I will just
	 * delete the whole database file. 
	 */
	private void setupDB() {
		Persistence.wipedb() ; 
	}

	//Expecting return of null for removed service from database using findService() method
	@Test
	public void removeService1() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & findService | Against: removing existing service");
		ApplicationLogic A = new ApplicationLogic();		
		
		int sid = A.addService("Banana Delivery", 100);		
		A.removeService(sid);
		
		assertTrue(A.findService(sid) == null);
	}
	
	//Expecting return of false for removed service from database using serviceExists() method
	@Test
	public void removeService2() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & serviceExists | Against: removing existing service");
		ApplicationLogic A = new ApplicationLogic();		
		
		int sid = A.addService("Banana Delivery", 100);		
		A.removeService(sid);
		
		assertTrue(A.serviceExists(sid) == false);
	}
		
	//Expecting return of null for removed service from database using findService() 
	//Testing against use of removeService() on empty database and non-existing ID 
	@Test
	public void removeService3() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & findService | Against: removing non-exisiting negative ID from empty DB");
		ApplicationLogic A = new ApplicationLogic();		
		
		int sid = -1;		
		A.removeService(sid);
		
		assertTrue(A.findService(sid) == null);
	}

	//Expecting return of null for removed service from database using findService() 
	//Testing against use of removeService() on empty database and non-existing ID 
	@Test
	public void removeService4() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & findService | Against: removing non-exisiting positive ID from empty DB");
		ApplicationLogic A = new ApplicationLogic();		
		
		int sid = 69;		
		A.removeService(sid);
		
		assertTrue(A.findService(sid) == null);
	}
	
	//Expecting return of null for removed service from database using findService() 
	//Testing against use of removeService() on empty database and non-existing ID 
	@Test
	public void removeService5() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & findService | Against: removing non-exisiting negative ID from populated DB");
		ApplicationLogic A = new ApplicationLogic();		
		
		A.addService("Banana Delivery", 100);
		int sid = -1;
		A.removeService(sid);
		
		assertTrue(A.findService(sid) == null);
	}
	
	//Expecting return of null for removed service from database using findService() 
	//Testing against use of removeService() on populated database and non-existing ID 
	@Test
	public void removeService6() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService & findService | Against: removing non-exisiting positive ID from populated DB");
		ApplicationLogic A = new ApplicationLogic();		
		
		A.addService("Banana Delivery", 100);
		int sid = 69;
		A.removeService(sid);
		 
		assertTrue(A.findService(sid) == null);
	}
	
	//
	@Test
	public void removeService7() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: removeService | Against: Removing Service with existing Customers involved in Participation with Service to be removed");
		ApplicationLogic A = new ApplicationLogic();
		
		Customer c0 = new Customer(1, "Bob", "");
		Customer c1 = new Customer(2, "Joe", "");
		Customer c2 = new Customer(3, "Curly", "");
		Customer c3 = new Customer(4, "Mike", "");
		
		Service s = new Service(1, "Walt Disney Delivery", 100);
		
		int cid0 = A.addCustomer(c0.name, c0.email);
		int cid1 = A.addCustomer(c1.name, c1.email);
		int cid2 = A.addCustomer(c2.name, c2.email);
		int cid3 = A.addCustomer(c3.name, c3.email);
		
		int sid = A.addService(s.name, s.price);
		
		A.addParticipation(cid0, sid);
		A.addParticipation(cid1, sid);
		A.addParticipation(cid2, sid);
		A.addParticipation(cid3, sid);
		
		A.removeService(sid);
		
		assertTrue(A.findService(sid) == null);
	}

	//Expected return of empty Map, since there are no entries in the database
	@Test
	public void resolve1() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: resolve | Against: Calling resolve on empty Database");
		ApplicationLogic A = new ApplicationLogic();
		
		Map<Customer, Integer> ci = new HashMap<Customer, Integer>();
		
		ci = A.resolve();
		assertTrue(ci.isEmpty());
	}
	
	//Expected return of 950 euros after applied discount for total cost of 1000 
	@Test
	public void resolve2() {
		setupDB();
		System.out.println("** Testing Class: ApplicationLogic | Method: resolve | Against: ");
		Discount_1000 d = new Discount_1000();		
		ApplicationLogic A = new ApplicationLogic();						
						
		int cid = A.addCustomer("Mr.Resolve", "");
		int sid = A.addService("Resolving", 1000);
		
		Customer c = A.findCustomer(cid);
		Service s = A.findService(sid);		
		Participation p = new Participation(c, s);
		
		A.addParticipation(cid, sid);		
				
		c.discounts.add(d);
		c.participations.add(p);
		
		Map<Customer, Integer> ci = new HashMap<Customer, Integer>();
		ci = A.resolve();
		int tmp = 0;
		for(Customer customer : ci.keySet()) {
			tmp = ci.get(customer);
		}
		float customerCost = (float) tmp / 100f;
		System.out.println(customerCost);
		assertTrue(customerCost == 1000-50);
	}	
}
