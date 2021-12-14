package telran.b7a.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.dto.SuccessResponseDto;
import telran.b7a.forum.service.ForumService;

@RestController
public class ForumController {
	
	ForumService forumService;
	
	@Autowired
	public ForumController(ForumService forumService) {
		this.forumService = forumService;
	}
	
	@PostMapping("/forum/post/{author}")
	public SuccessResponseDto postRegister(@RequestBody PostDto postDto) {
		return forumService.addPost(postDto);
	}
	
	@GetMapping("/forum/post/{id}")
	public PostDto findPostById(@PathVariable String id) {
		return forumService.findPost(id);
		
	}

}
