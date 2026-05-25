package se.centernode.javaapitest;

import org.springframework.stereotype.Component;

@Component
public class RegistrationService {
	public String register(RegistrationRequest request) {
		
		return "User registered";
	}
}
