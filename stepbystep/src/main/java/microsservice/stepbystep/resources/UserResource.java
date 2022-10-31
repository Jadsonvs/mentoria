package microsservice.stepbystep.resources;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microsservice.stepbystep.entities.User;
import microsservice.stepbystep.services.UserService;

@RestController
public class UserResource {

	@Autowired
	public UserService userService;

	Logger logger = LoggerFactory.getLogger(UserResource.class);

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findUserByFilter(@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer age) {
		try {
			List<User> list = new ArrayList<>();
			if (name != null) {
				list = userService.getUserByName(name);
			}
			if (age != null) {
				list = userService.getUserByAge(age);
			}
			if (name != null && age != null) {
				list = userService.getUserByNameAndAge(name, age);
			}
			if (name == null && age == null) {
				list = userService.getAllUsers();
			}
			return ResponseEntity.ok(list);
		}
		catch(NullPointerException e) {
			return ResponseEntity.notFound().build();
		}
		
	}

	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserbyId(@PathVariable UUID id) {
		try {
			User user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (InputMismatchException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		try {
			user.setId(UUID.randomUUID());
			userService.addUser(user);
		} catch (InputMismatchException e) {
			return ResponseEntity.unprocessableEntity().body("ID already exist in the system.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUser(@RequestBody User updateUser) {
		try {
			userService.updateUser(updateUser);
			return ResponseEntity.accepted().build();
		}
		catch (InputMismatchException e) {
			return ResponseEntity.status(HttpStatus.CREATED).body("ID not found in the database."
					+ " It was created a new user.");
		}
	}

	@PatchMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateOnlyOneAttribute(@RequestBody User updateUser) {
		try {
			userService.updateOnlyOneAttribute(updateUser);
			return ResponseEntity.accepted().build();
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		try {
			userService.deleteUser(id);
		} catch (NullPointerException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
