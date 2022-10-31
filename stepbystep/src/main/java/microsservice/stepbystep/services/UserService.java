package microsservice.stepbystep.services;

import java.util.List;
import java.util.UUID;

import microsservice.stepbystep.entities.User;

public interface UserService {
	
	public List<User> getAllUsers();
	
	public void addUser(User user);
	
	public void deleteUser(UUID id);
	
	public void updateUser(User updateUser);
	
	public void updateOnlyOneAttribute(User updateUser);
	
	public User getUserById(UUID id);
	
	public List<User> getUserByName(String name);
	
	public List<User> getUserByAge(Integer age);
	
	public List<User> getUserByNameAndAge(String name, Integer age);

}
