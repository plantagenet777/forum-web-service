package telran.b7a.accounting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
@NonNull
public class UserLoginDto {
	String login;
    String password;

}
