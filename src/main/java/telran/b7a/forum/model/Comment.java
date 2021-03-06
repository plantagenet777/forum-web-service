package telran.b7a.forum.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import telran.b7a.forum.model.Comment;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = { "user", "dateCreated" })
@ToString
public class Comment {
	@Setter
	String user;
	@Setter
	String message;
	@Setter
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime dateCreated;
	int likes;

	public Comment(String user, String message) {
		dateCreated = LocalDateTime.now();
		this.user = user;
		this.message = message;
		
	}
	
	public void addLike() {
		likes++;
	}

}
