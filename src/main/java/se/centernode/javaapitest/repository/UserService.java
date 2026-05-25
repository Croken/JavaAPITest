package se.centernode.javaapitest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import se.centernode.javaapitest.entity.User;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private UserRepository repo;
		
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return repo.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User not found: " + email)
		);
	}

	public User save(User user) throws AssertionError {
		if (user.getId() == null) {
			Optional<User> byEmail = repo.findByEmail(user.getEmail());
			if (byEmail.isPresent()) {
				throw new AssertionError("User already exists: " + user.getEmail());
			}
		}
		return repo.save(user);
	}

	public Optional<User> findById(Long id) {
		return repo.findById(id);
	}

	public void delete(User saveUser) {
		repo.delete(saveUser);
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public Optional<User> findByEmail(String string) {
		return repo.findByEmail(string);
	}
}
