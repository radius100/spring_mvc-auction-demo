package auction.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class RateUtilsTest {

	//private RateUtils rateUtils;
	
	@Before
	public void setUp() throws Exception {
   // 	rateUtils = new RateUtils();
    }
	
    @Test
	public void testGetRateAdvs() throws Exception {
    	 
    	String resString = RateUtils.getRateAdvs(10, 10);
    
    	assertEquals("12",resString);
    	
      }
    
    @Ignore
    @Test
   	public void testGetRateAdvs1() throws Exception {
       	 
       	String resString = RateUtils.getRateAdvs(10, 10);
       
       	assertEquals("112",resString);
       	
    }
       
}