package microsservice.stepbystep.repository.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	public List<User> dataBase = new ArrayList<>();
	
	@Override
	public void add(User user) {
		dataBase.add(user);
	}

	@Override
	public void delete(User user) {
		dataBase.remove(user);
	}
	
	@Override
	public List<User> getUsers() {
		return dataBase;
	}
	
}
