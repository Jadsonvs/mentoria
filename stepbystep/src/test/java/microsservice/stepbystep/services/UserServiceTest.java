package microsservice.stepbystep.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;
import microsservice.stepbystep.services.exceptions.UserNameAlreadyExistException;
import microsservice.stepbystep.services.exceptions.UserNotFoundException;
import microsservice.stepbystep.services.implementation.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	public void addUserTest() throws UserNameAlreadyExistException {
		User user = new User();
		user.setId(UUID.fromString("0d799603-6acf-49cb-842c-16e0f001c416"));
		user.setName("Jadson Viana");
		user.setAge(28);
		
		userServiceImpl.addUser(user);
		verify(userRepository, times(1)).add(user);
		
		assertNotNull(user);
	}
	
	@Test
	public void getUserById() throws UserNameAlreadyExistException, UserNotFoundException {
		User user = new User();
		user.setId(UUID.fromString("0d799603-6acf-49cb-842c-16e0f001c416"));
		user.setName("Jadson Viana");
		user.setAge(28);
		
		userServiceImpl.addUser(user);
		
		when(userServiceImpl.getUserById(any(UUID.class))).thenReturn(user);
		
		assertEquals("Jadson Viana", user.getName());
		
	}
	
}
