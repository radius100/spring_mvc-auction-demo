package auction.application;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

//@Service
//@Scope("global-session")
public class ItemManager {

	private String test;
	
	public ItemManager() {
		test="Test";
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	
}
