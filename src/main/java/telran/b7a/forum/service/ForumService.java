package telran.b7a.forum.service;

import telran.b7a.forum.dto.NewCommentDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.PostDto;

public interface ForumService {
	
	 PostDto addNewPost(NewPostDto newPostDto, String author);
	 
	 PostDto getPost(String id);
	 
	 PostDto removePost(String id);
	 
	 PostDto updatePost(NewPostDto postUpdateDto, String id);
	 
	 void addLike(String id);
	 
	 PostDto addComment(String id, String author, NewCommentDto newCommentDto);
	 
	 Iterable<PostDto> findPostByAutor(String author);

}
