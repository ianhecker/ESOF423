package Participation;
import org.junit.* ;
import static org.junit.Assert.* ;

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
	
	/*
	@Test
	public void test1() {
		// We'll always begin by reseting the database. This makes sure
		// the test start from a clean, well defined state of the database.
		// In this case it would be just an empty database, though it 
		// doesn't have to be like that.
		setupDB() ;
		
		System.out.println("** Testing add customer...") ;
		
		// Creating the target thing you want to test:
		ApplicationLogic SUT = new ApplicationLogic() ;
		
		// Now let's perform some testing. If we add a customer to the system,
		// test that this customer should then be really added to the system:
		int duffyID = SUT.addCustomer("Duffy Duck", "") ;
		Customer C = SUT.findCustomer(duffyID) ;
		assertTrue(C.name.equals("Duffy Duck")) ;
		assertTrue(C.email.equals("")) ;		
	}
	
	// Another example...
	@Test
	public void test2() {
		setupDB() ;
		ApplicationLogic SUT = new ApplicationLogic() ;
		
		System.out.println("** Testing getCostToPay ...") ;
		
		int duffyID = SUT.addCustomer("Duffy Duck", "") ;
		int flowerServiceID = SUT.addService("Flowers online shop", 100) ;
		// let Duffy but 2x participations on Flower-online:
		SUT.addParticipation(duffyID, flowerServiceID) ;
		SUT.addParticipation(duffyID, flowerServiceID) ;

		// Now let's check if the system correctly calculates the participation
		// cost of Duffy:
		Customer C = SUT.findCustomer(duffyID) ;
		assertTrue(C.getCostToPay() == 200) ;
	}
	*/
	
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
