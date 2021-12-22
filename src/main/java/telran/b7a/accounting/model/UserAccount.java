package telran.b7a.accounting.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = { "login" })
@Document(collection = "users")
@ToString
public class UserAccount {
	@Id
	String login;
	String password;
	String firstName;
	String lastName;
	@Singular
	Set<String> roles;
	
	
	public UserAccount(String login1, String password, String firstName, String lastName) {
		this.login = login1;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = new HashSet<String>();
	}
	
	public UserAccount() {
		roles = new HashSet<String>();
	}

	public boolean addRole(String role) {
		return roles.add(role);
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}

}
