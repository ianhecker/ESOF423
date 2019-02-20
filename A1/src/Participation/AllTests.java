package Participation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationLogic_test.class, Customer_test.class, Discount_1000_test.class, Discount_5pack_test.class })
public class AllTests {
	
}
