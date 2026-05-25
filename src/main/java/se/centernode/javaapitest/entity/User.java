package se.centernode.javaapitest.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.centernode.javaapitest.enums.UserRole;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

		@Id
//		@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
//		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(unique = true)
		private String email;
		
		private String password;
		
		@Enumerated(EnumType.STRING)
		private UserRole role;
		
		private Boolean verified;
		
		private Boolean locked;
		
		public User(String email, String password, UserRole role) {
			this.email = email;
			this.password = password;
			this.role = role;
			this.verified = false;
			this.locked = false;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
			return Collections.singleton(authority);
		}

		@Override
		public String getPassword() {
			return password;
		}

		@Override
		public String getUsername() {
			return email;
		}
		
		@Override
		public boolean isAccountNonExpired() {
			return true;
		}
		
		@Override
		public boolean isAccountNonLocked() {
			return !locked;
		}
		
		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}
		
		@Override
		public boolean isEnabled() {
			return true;
		}
}
