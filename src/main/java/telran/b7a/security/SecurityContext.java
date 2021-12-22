package telran.b7a.security;

public interface SecurityContext {
	boolean addUser(UserProfile user);
	
	UserProfile removeUserProfile(String login);
	
	UserProfile getUser(String login);

}
