package microsservice.stepbystep.repository;

import java.util.List;

import microsservice.stepbystep.entities.User;

public interface UserRepository {
	
	public void add(User user);
	
	public void delete(User user);
	
	public List<User> getUsers();
	
}
