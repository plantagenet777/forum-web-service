package telran.b7a.forum.service;

import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.dto.SuccessResponseDto;

public interface ForumService {
	
	 SuccessResponseDto addPost(PostDto postDto);
	 
	 PostDto findPost(String id);

}
