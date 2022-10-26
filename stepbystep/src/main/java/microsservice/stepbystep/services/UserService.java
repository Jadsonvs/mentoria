package microsservice.stepbystep.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	
	public void deleteUser(UUID id) {
		User user = new User();
		
		for(User userFind : userRepository.getUsers()) {
			if(userFind.getId().equals(id)) {
				user = userFind;
			}
		}
		userRepository.delete(user);
	}
	
	public void updateUser(User updateUser) {
		for(User user : userRepository.getUsers()) {
			if(user.getId().equals(updateUser.getId())) {
				user.setName(updateUser.getName());
				user.setAge(updateUser.getAge());
			}
		}
	}
	
	public void updateOnlyOneAttribute(User updateUser) {
		for(User user : userRepository.getUsers()) {
			if(user.getId().equals(updateUser.getId())) {
				if(updateUser.getName() != null) {
					user.setName(updateUser.getName());
				}
				
				if (updateUser.getAge() != null ) {
					user.setAge(updateUser.getAge());
				}
			}
		}
	}
	
	public User getUserById(UUID id) {
		for(User user : userRepository.getUsers()) {
			if(id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> getUserByName(String name) {
		List<User> newList = new ArrayList<>();
		for(User user : userRepository.getUsers()) {
			if(user.getName().startsWith(name)) {
				newList.add(user);
			}
		}
		return newList;
	}
	
	public List<User> getUserByAge(Integer age) {
		List<User> newList = new ArrayList<>();
		for(User user : userRepository.getUsers()) {
			if(user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		return newList;
	}
	
	public List<User> getUserByNameAndAge(String name, Integer age){
		List<User> newList = new ArrayList<>();
		for(User user : userRepository.getUsers()) {
			if(user.getName().equals(name) && user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		return newList;
	}

}
