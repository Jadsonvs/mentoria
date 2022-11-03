package microsservice.stepbystep.services;

import java.util.List;
import java.util.UUID;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.services.exceptions.UserNameAlreadyExistException;
import microsservice.stepbystep.services.exceptions.UserNotFoundException;

public interface UserService {
	
	/**
	 * The method getAllUser() is responsible to return all the database's users.
	 */
	public List<User> getAllUsers();
	
	/**
	 * The method addUser() is responsible to register the user in the database. If
	 * user already exist in the database, an exception will be throw and the user
	 * won't be registered.
	 * @throws UserNameAlreadyExistException 
	 */
	public void addUser(User user) throws UserNameAlreadyExistException;
	
	/**
	 * The method deleteUser() is responsible to delete an user in the database. If
	 * ID didn't find in the database, an exception will be throw and the user won't
	 * be deleted.
	 * @throws UserNotFoundException 
	 */
	public void deleteUser(UUID id) throws UserNotFoundException;
	
	/**
	 * The method updateUser() is responsible to update user's name and age. If ID
	 * already in the database, an exception will be throw and a new user will be
	 * created.
	 * @throws UserNotFoundException 
	 * @throws UserNameAlreadyExistException 
	 */
	public void updateUser(User updateUser) throws UserNotFoundException, UserNameAlreadyExistException;
	
	/**
	 * The method updateOnlyOneAttribute() is responsible to update user's name or
	 * age. If ID already in the database, an exception will be throw and a new user
	 * won't be update.
	 * @throws UserNotFoundException 
	 */
	public void updateOnlyOneAttribute(User updateUser) throws UserNotFoundException;
	
	/**
	 * The method getUserById(UUID id) is responsible to search an user by ID. If ID
	 * is not exist in the database, an exception will be throw warning about it.
	 * @throws UserNotFoundException 
	 */
	public User getUserById(UUID id) throws UserNotFoundException;
	
	/**
	 * The method getUserByName(String name) is responsible to search an user by name. If name
	 * is not exist in the database, an exception will be throw warning about it.
	 * @throws UserNotFoundException 
	 */
	public List<User> getUserByName(String name) throws UserNotFoundException;
	
	/**
	 * The method getUserByAge(Integer age) is responsible to search an user by age. If age
	 * is not exist in the database, an exception will be throw warning about it.
	 * @throws UserNotFoundException 
	 */
	public List<User> getUserByAge(Integer age) throws UserNotFoundException;
	
	/**
	 * The method getUserByNameAndAge(String name, Integer age) is responsible to search an user by
	 * name and age. If name or age are not exist in the database, an exception will be throw 
	 * coming of the method getUserByName(String name) or getUserByAge(Integer age) warning about it.
	 */
	public List<User> getUserByNameAndAge(String name, Integer age);

}
