package telran.b7a.accounting.service;

import telran.b7a.accounting.dto.RolesResponseDto;
import telran.b7a.accounting.dto.UserAccountResponseDto;
import telran.b7a.accounting.dto.UserLoginDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserUpdateDto;

public interface UserAccountService {

	UserAccountResponseDto addUser(UserRegisterDto userRegisterDto);

	UserAccountResponseDto getUser(String login);
	
	UserAccountResponseDto removeUser(String login);
	
	UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto);
	
	RolesResponseDto addRoleList(String login, String role);
	  
	RolesResponseDto deleteRoleList(String login, String role);
	
	void changePassword(UserLoginDto password);

}