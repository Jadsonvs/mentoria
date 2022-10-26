package microsservice.stepbystep.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userService.userRepository.getUsers();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/users/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> findUserByFilter(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
		List<User> list = new ArrayList<>();
		if(name != null) {
			list = userService.getUserByName(name);
		}
		if (age != null) {
			list = userService.getUserByAge(age);
		}
		if (name != null && age != null) {
			list = userService.getUserByNameAndAge(name, age);
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserbyId(@PathVariable UUID id){
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		userService.userRepository.add(user);
		return ResponseEntity.accepted().build();
	}
	
	@PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<User> updateUser(@RequestBody User updateUser) {
			userService.updateUser(updateUser);
			return ResponseEntity.accepted().build();
	}
	
	@PatchMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateOnlyOneAttribute(@RequestBody User updateUser){
		userService.updateOnlyOneAttribute(updateUser);
		System.out.println("CHamou o método!");
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> delete(@PathVariable UUID id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

}
