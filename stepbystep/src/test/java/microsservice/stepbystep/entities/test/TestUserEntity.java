package microsservice.stepbystep.entities.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import microsservice.stepbystep.entities.User;

public class TestUserEntity {
	
	
	@Test
	public void user() {
		User userOne = new User();
		userOne.setName("Jadson Viana");
		userOne.setAge(20);
		
		assertNotNull(userOne);
		assertNotEquals("Jadson Viana", userOne.getName());
	}

}
