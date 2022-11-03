package microsservice.stepbystep.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;
import microsservice.stepbystep.services.UserService;
import microsservice.stepbystep.services.exceptions.UserNameAlreadyExistException;
import microsservice.stepbystep.services.exceptions.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	public List<User> getAllUsers() {
		logger.info("User's list required " + userRepository.getUsers().toString());
		return userRepository.getUsers();
	}

	public void addUser(User user) throws UserNameAlreadyExistException {
		for (User userCompare : getAllUsers()) {
			if (userCompare.getName().equals(user.getName())) {
				logger.warn("User' name \"" + user.getName() + "\" already exist in the database");
				throw new UserNameAlreadyExistException("User' name already exist in the database");
			}
		}
		userRepository.add(user);
		logger.info("User \"" + user + "\" sucessfully added in the database.");
	}

	public void deleteUser(UUID id) throws UserNotFoundException {
		User user = new User();
		user = null;
		for (User userFind : userRepository.getUsers()) {
			if (userFind.getId().equals(id)) {
				user = userFind;
			}
		}
		if (user == null) {
			logger.warn("User's ID \"" + id + "\" not found in the database to delete");
			throw new UserNotFoundException("User not found in the database");
		}
		userRepository.delete(user);
		logger.info("User \"" + user.getName() + "\" sucessfully deleted.");

	}

	public void updateUser(User updateUser) throws UserNotFoundException, UserNameAlreadyExistException {
		User userCompare = new User();
		userCompare = null;
		for (User user : userRepository.getUsers()) {
			if (user.getId().equals(updateUser.getId())) {
				logger.info("Upadating user's name and age \"" + user + "\" in the database.");
				user.setName(updateUser.getName());
				user.setAge(updateUser.getAge());
				logger.info("User's name and age sucessfully updated to \"" + updateUser + "\" in the database");
				userCompare = user;
			}
		}
		if (userCompare == null) {
			addUser(updateUser);
			logger.warn("User's ID \"" + updateUser.getId() + "\" not found in the database. It will be created a new user \"" + updateUser + "\" in the database");
			throw new UserNotFoundException("User's ID not found in the database.");
		}
	}

	public void updateOnlyOneAttribute(User updateUser) throws UserNotFoundException {
		User userCompare = new User();
		userCompare = null;
		for (User user : userRepository.getUsers()) {
			if (user.getId().equals(updateUser.getId())) {
				if (updateUser.getName() != null) {
					logger.info("Upadating user's name \"" + user.getName() + "\" in the database");
					user.setName(updateUser.getName());
					logger.info("User's name sucessfully updated to \"" + updateUser.getName() + "\" in the database");
				}

				if (updateUser.getAge() != null) {
					logger.info("Upadating user's age \"" + user + "\" in the database");
					user.setAge(updateUser.getAge());
					logger.info("User's age sucessfully updated to \"" + updateUser.getAge() + "\" in the database");
				}
				userCompare = user;
			}
		}
		if (userCompare == null) {
			logger.warn("User's ID \"" + updateUser.getId() + "\" not found in the database to update");
			throw new UserNotFoundException("User's ID not found in the database");
		}
	}

	public User getUserById(UUID id) throws UserNotFoundException {
		logger.info("Searching user by ID \"" + id + "\" in the database");
		for (User user : userRepository.getUsers()) {
			if (id.equals(user.getId())) {
				logger.info(
						"User \"" + "[id=" + user.getId() + ", name=" + user.getName() + "]" + "\" sucessfully found in the database.");
				return user;
			}
		}
		logger.info("User's ID \"" + id + "\" not found in the database");
		throw new UserNotFoundException("User's ID not found in the database");
	}

	public List<User> getUserByName(String name)throws UserNotFoundException {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name \"" + name + "\" in the database");
		for (User user : userRepository.getUsers()) {
			if (user.getName().startsWith(name)) {
				newList.add(user);
			}
		}
		if(newList.isEmpty()) {
			logger.warn("No one user with name \"" + name + "\" was found in the database.");
			throw new UserNotFoundException("User's name not found in the database");
		}
		logger.info("Users found: " + newList.toString() + " in the database");
		return newList;

	}

	public List<User> getUserByAge(Integer age) throws UserNotFoundException {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by age \"" + age + "\" in the database");
		for (User user : userRepository.getUsers()) {
			if (user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		if(newList.isEmpty()) {
			logger.warn("No one user age \"" + age + "\" was found in the database.");
			throw new UserNotFoundException("User's age not found in the database");
		}
		logger.info("Users found: " + newList.toString() + " in the database");
		return newList;
	}

	public List<User> getUserByNameAndAge(String name, Integer age) {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name \"" + name + "\" and age \"" + age + "\" in the database");
		for (User user : userRepository.getUsers()) {
			if (user.getName().startsWith(name) && user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}

}
