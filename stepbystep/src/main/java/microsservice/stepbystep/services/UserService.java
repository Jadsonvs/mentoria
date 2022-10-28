package microsservice.stepbystep.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	public void deleteUser(UUID id) {
		User user = new User();
		
		for(User userFind : userRepository.getUsers()) {
			if(userFind.getId().equals(id)) {
				user = userFind;
			}
		}
		logger.info("User \"" + user.getName() + "\" sucessfully deleted.");
		userRepository.delete(user);
	}
	
	public void updateUser(User updateUser) {
		for(User user : userRepository.getUsers()) {
			if(user.getId().equals(updateUser.getId())) {
				logger.info("Upadating user's name and age \"" + user + "\".");
				user.setName(updateUser.getName());
				user.setAge(updateUser.getAge());
				logger.info("User's name and age sucessfully updated to \"" + updateUser + "\"");
			}
		}
	}
	
	public void updateOnlyOneAttribute(User updateUser) {
		for(User user : userRepository.getUsers()) {
			if(user.getId().equals(updateUser.getId())) {
				logger.info("User \"" + user + "\" to be updated.");
				if(updateUser.getName() != null) {
					logger.info("Upadating user's name \"" + user.getName() + "\".");
					user.setName(updateUser.getName());
					logger.info("User's name sucessfully updated to \"" + updateUser.getName() + "\"");
				}
				
				if (updateUser.getAge() != null ) {
					logger.info("Upadating user's name \"" + user.getAge() + "\".");
					user.setAge(updateUser.getAge());
					logger.info("User's age sucessfully updated to \"" + updateUser.getAge() + "\"");
				}
			}
		}
	}
	
	public User getUserById(UUID id) {
		logger.info("Searching user by ID \"" + id + "\"");
		for(User user : userRepository.getUsers()) {
			if(id.equals(user.getId())) {
				logger.info("User \"" + "[id=" + user.getId() + ", name=" + user.getName() + "]" + "\" sucessfully found.");
				return user;
			}
		}
		logger.info("User not found");
		return null;
	}
	
	public List<User> getUserByName(String name) {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name \"" + name + "\"");
		for(User user : userRepository.getUsers()) {
			if(user.getName().startsWith(name)) {
				newList.add(user);
			}
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}
	
	public List<User> getUserByAge(Integer age) {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by age \"" + age + "\"");
		for(User user : userRepository.getUsers()) {
			if(user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}
	
	public List<User> getUserByNameAndAge(String name, Integer age){
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name and age \"" + name + "\" and \"" + age + "\"");
		for(User user : userRepository.getUsers()) {
			if(user.getName().equals(name) && user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}

}
