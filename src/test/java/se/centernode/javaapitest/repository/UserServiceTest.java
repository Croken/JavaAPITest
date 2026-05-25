package se.centernode.javaapitest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import se.centernode.javaapitest.entity.User;
import se.centernode.javaapitest.enums.UserRole;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTest {
	
	@Autowired
	UserService service;
	
	User user = new User("anders.orback@gmail.com", "pasword123", UserRole.USER);
	
	// Creation
	@Test
	@Order(1)
	void createTest() {
		assertEquals(null, user.getId());
		assertEquals("anders.orback@gmail.com", user.getEmail());
		assertEquals("pasword123", user.getPassword());
		assertEquals(UserRole.USER, user.getRole());
		assertEquals(false, user.getVerified());
		assertEquals(false, user.getLocked());
		
		User saveUser = service.save(user);
		assertEquals(Long.valueOf(1), saveUser.getId());
		assertEquals(user.getEmail(), saveUser.getEmail());
		assertEquals(user.getPassword(), saveUser.getPassword());
		assertEquals(user.getRole(), saveUser.getRole());
		assertEquals(user.getVerified(), saveUser.getVerified());
		assertEquals(user.getLocked(), saveUser.getLocked());
	}
	
	// Save & retrieve by Id
	@Test
	@Order(2)
	void saveAndRetriveTest() {
		User saveUser = service.save(user);
		Optional<User> foundById = service.findById(saveUser.getId());
		assertTrue(foundById.isPresent());
		
		User userById = foundById.get();
		assertEquals(saveUser.getId(), userById.getId(), "User id should be the same: " + userById.getId());
		assertEquals(saveUser.getEmail(), userById.getEmail(), "Email should be the same: " + userById.getEmail());
		assertEquals(saveUser.getPassword(), userById.getPassword(), "password should be the same: " + userById.getPassword());
		assertEquals(saveUser.getRole(), userById.getRole(), "Role should be the same: " + userById.getRole());
		assertEquals(saveUser.getVerified(), userById.getVerified(), "Verified should be the same: " + userById.getVerified());
		assertEquals(saveUser.getLocked(), userById.getLocked(), "Locked should be the same: " + userById.getLocked());
		
	}
	
	// Update
	@Test
	@Order(3)
	void updateTest() {
		User saveUser = service.save(user);
		service.save(saveUser);
		saveUser.setVerified(true);
		service.save(saveUser);
		
		Optional<User> foundById = service.findById(saveUser.getId());
		assertTrue(foundById.isPresent());
		
		User userById = foundById.get();
		assertEquals(saveUser.getId(), userById.getId(), "User id should be the same: " + userById.getId());
		assertEquals(saveUser.getEmail(), userById.getEmail(), "Email should be the same: " + userById.getEmail());
		assertEquals(saveUser.getPassword(), userById.getPassword(), "password should be the same: " + userById.getPassword());
		assertEquals(saveUser.getRole(), userById.getRole(), "Role should be the same: " + userById.getRole());
		assertEquals(true, userById.getVerified(), "Verified should be updated: " + userById.getVerified());
		assertEquals(saveUser.getLocked(), userById.getLocked(), "Locked should be the same: " + userById.getLocked());
		
	}
	
	// Delete
	@Test
	@Order(4)
	void deleteTest() {
		User saveUser = service.save(user);
		service.delete(saveUser);
		Optional<User> foundById = service.findById(saveUser.getId());
		assertTrue(!foundById.isPresent());
	}
	
	// find All
	@Test
	@Order(5)
	void findAllTest() {
		service.save(new User("anders.orback@gmail.com", "pasword123", UserRole.USER));
		List<User> all = service.findAll();
		assertEquals(1, all.size());
		try {
			service.save(new User("anders.orback@gmail.com", "pasword123", UserRole.USER));
		} catch (AssertionError e) {
			assertTrue(e.getMessage().contains("already exists"));
		}
		service.save(new User("other.orback@gmail.com", "pasword123", UserRole.USER));
		all = service.findAll();
		assertEquals(2, all.size());
	}
	
	// Find by email
	@Test
	@Order(6)
	void findByEmailTest() {
		User saveUser = service.save(new User("anders.orback@gmail.com", "pasword123", UserRole.USER));
		service.save(new User("other.orback@gmail.com", "pasword123", UserRole.USER));
		Optional<User> foundByEmail = service.findByEmail("anders.orback@gmail.com");
		assertTrue(foundByEmail.isPresent());
		assertEquals(saveUser.getId(), foundByEmail.get().getId());
	}
}
