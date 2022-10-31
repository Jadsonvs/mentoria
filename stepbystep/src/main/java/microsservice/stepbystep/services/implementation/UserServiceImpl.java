package microsservice.stepbystep.services.implementation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.repository.UserRepository;
import microsservice.stepbystep.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * The method getAllUser() is responsible to return all the database's users.
	 */
	public List<User> getAllUsers() {
		logger.info("Users list required " + userRepository.getUsers().toString());
		return userRepository.getUsers();
	}

	/**
	 * The method addUser() is responsible to register the user in the database. If
	 * user already exist in the database, an exception will be throw and the user
	 * won't be registered.
	 */
	public void addUser(User user) throws InputMismatchException {
		for (User userCompare : getAllUsers()) {
			if (userCompare.getName().equals(user.getName())) {
				logger.warn("User \"" + user + "\" already exist in the database");
				throw new InputMismatchException();
			}
		}
		userRepository.add(user);
		logger.info("User \"" + user + "\" sucessfully added.");
	}

	/**
	 * The method deleteUser() is responsible to delete an user in the database. If
	 * ID didn't find in the database, an exception will be throw and the user won't
	 * be deleted.
	 */
	public void deleteUser(UUID id) throws NullPointerException {
		User user = new User();
		user = null;
		for (User userFind : userRepository.getUsers()) {
			if (userFind.getId().equals(id)) {
				user = userFind;
			}
		}
		if (user == null) {
			logger.warn("ID \"" + id + "\" not found to delete");
			throw new NullPointerException();
		}
		userRepository.delete(user);
		logger.info("User \"" + user.getName() + "\" sucessfully deleted.");

	}

	/**
	 * The method updateUser() is responsible to update user's name and age. If ID
	 * already in the database, an exception will be throw and a new user will be
	 * created.
	 */
	public void updateUser(User updateUser) throws InputMismatchException {
		User userCompare = new User();
		userCompare = null;
		for (User user : userRepository.getUsers()) {
			if (user.getId().equals(updateUser.getId())) {
				logger.info("Upadating user's name and age \"" + user + "\".");
				user.setName(updateUser.getName());
				user.setAge(updateUser.getAge());
				logger.info("User's name and age sucessfully updated to \"" + updateUser + "\"");
				userCompare = user;
			}
		}
		if (userCompare == null) {
			addUser(updateUser);
			throw new InputMismatchException();
		}
	}

	/**
	 * The method updateOnlyOneAttribute() is responsible to update user's name or
	 * age. If ID already in the database, an exception will be throw and a new user
	 * won't be update.
	 */
	public void updateOnlyOneAttribute(User updateUser) {
		User userCompare = new User();
		userCompare = null;
		for (User user : userRepository.getUsers()) {
			if (user.getId().equals(updateUser.getId())) {
				logger.info("User \"" + user + "\" to be updated.");
				if (updateUser.getName() != null) {
					logger.info("Upadating user's name \"" + user.getName() + "\".");
					user.setName(updateUser.getName());
					logger.info("User's name sucessfully updated to \"" + updateUser.getName() + "\"");
				}

				if (updateUser.getAge() != null) {
					logger.info("Upadating user's name \"" + user.getAge() + "\".");
					user.setAge(updateUser.getAge());
					logger.info("User's age sucessfully updated to \"" + updateUser.getAge() + "\"");
				}
				userCompare = user;
			}
		}
		if (userCompare == null) {
			logger.warn("ID \"" + updateUser + "\" not found to update");
			throw new NullPointerException();
		}
	}

	/**
	 * The method getUserById(UUID id) is responsible to search an user by ID. If ID
	 * is not exist in the database, an exception will be throw warning about it.
	 */
	public User getUserById(UUID id) throws InputMismatchException {
		logger.info("Searching user by ID \"" + id + "\"");
		for (User user : userRepository.getUsers()) {
			if (id.equals(user.getId())) {
				logger.info(
						"User \"" + "[id=" + user.getId() + ", name=" + user.getName() + "]" + "\" sucessfully found.");
				return user;
			}
		}
		logger.info("ID \"" + id + "\" not found");
		throw new InputMismatchException();
	}

	/**
	 * The method getUserByName(String name) is responsible to search an user by name. If name
	 * is not exist in the database, an exception will be throw warning about it.
	 */
	public List<User> getUserByName(String name)throws NullPointerException {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name \"" + name + "\"");
		for (User user : userRepository.getUsers()) {
			if (user.getName().startsWith(name)) {
				newList.add(user);
			}
		}
		if(newList.isEmpty()) {
			logger.warn("Anyone user \"" + name + "\" was found in the database.");
			throw new NullPointerException();
		}
		logger.info("Users found: " + newList.toString());
		return newList;

	}

	/**
	 * The method getUserByName(String name) is responsible to search an user by age. If age
	 * is not exist in the database, an exception will be throw warning about it.
	 */
	public List<User> getUserByAge(Integer age) {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by age \"" + age + "\"");
		for (User user : userRepository.getUsers()) {
			if (user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		if(newList.isEmpty()) {
			logger.warn("Anyone user \"" + age + "\" was found in the database.");
			throw new NullPointerException();
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}

	/**
	 * The method getUserByNameAndAge(String name, Integer age) is responsible to search an user by
	 * name and age. If name or age are not exist in the database, an exception will be throw 
	 * coming of the method getUserByName(String name) or getUserByAge(Integer age) warning about it.
	 */
	public List<User> getUserByNameAndAge(String name, Integer age) {
		List<User> newList = new ArrayList<>();
		logger.info("Searching user by name and age \"" + name + "\" and \"" + age + "\"");
		for (User user : userRepository.getUsers()) {
			if (user.getName().startsWith(name) && user.getAge().equals(age)) {
				newList.add(user);
			}
		}
		logger.info("Users found: " + newList.toString());
		return newList;
	}

}
