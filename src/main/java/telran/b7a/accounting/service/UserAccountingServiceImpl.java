package telran.b7a.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.UserAccountRepository;
import telran.b7a.accounting.dto.RolesResponseDto;
import telran.b7a.accounting.dto.UserAccountResponseDto;
import telran.b7a.accounting.dto.UserLoginDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserUpdateDto;
import telran.b7a.accounting.dto.exceptions.UserExistsException;
import telran.b7a.accounting.dto.exceptions.UserNotFoundException;
import telran.b7a.accounting.model.UserAccount;

@Service
public class UserAccountingServiceImpl implements UserAccountService {

	UserAccountRepository repository;
	ModelMapper modelMapper;

	@Autowired
	public UserAccountingServiceImpl(UserAccountRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto) {
		if (repository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsException(userRegisterDto.getLogin());
		}
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		userAccount.addRole("USER".toUpperCase());
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto getUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto removeUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		repository.deleteById(login);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		if (!userUpdateDto.getFirstName().isBlank()) {
			userAccount.setFirstName(userUpdateDto.getFirstName());
		}
		if (!userUpdateDto.getLastName().isBlank()) {
			userAccount.setLastName(userUpdateDto.getLastName());
		}
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public RolesResponseDto addRoleList(String login, String role) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		if (!role.isBlank()) {
			userAccount.addRole(role.toUpperCase());
		}
		repository.save(userAccount);
		return modelMapper.map(userAccount, RolesResponseDto.class);
	}

	@Override
	public RolesResponseDto deleteRoleList(String login, String role) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException());
		if (userAccount.getRoles().contains(role.toUpperCase())) {
			userAccount.getRoles().remove(role.toUpperCase());
		} else {
			return modelMapper.map(userAccount, RolesResponseDto.class);
		}
		repository.save(userAccount);
		return modelMapper.map(userAccount, RolesResponseDto.class);
	}

	@Override
	public void changePassword(UserLoginDto user) {
		UserAccount userAccount = repository.findById(user.getLogin()).orElseThrow(() -> new UserNotFoundException());
		userAccount.setPassword(user.getPassword());
		repository.save(userAccount);
	}

}
