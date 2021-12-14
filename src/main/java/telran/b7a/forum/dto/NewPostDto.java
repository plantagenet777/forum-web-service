package telran.b7a.forum.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NewPostDto {
	String title;
	String content;
	Set<String> tags;

}
