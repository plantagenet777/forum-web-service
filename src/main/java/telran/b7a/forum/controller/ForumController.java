package telran.b7a.forum.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.forum.dto.DateDto;
import telran.b7a.forum.dto.NewCommentDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.service.ForumService;

@RestController
@RequestMapping("/forum")
public class ForumController {

	ForumService service;
	ModelMapper modelMapper;

	@Autowired
	public ForumController(ForumService service) {
		this.service = service;
	}

	@PostMapping("/post/{author}")
	public PostDto addPost(@RequestBody NewPostDto newPost, @PathVariable String author) {
		return service.addNewPost(newPost, author);
	}

	@GetMapping("/post/{id}")
	public PostDto getPost(@PathVariable String id) {
		return service.getPost(id);
	}

	@DeleteMapping("/post/{id}")
	public PostDto removePost(@PathVariable String id) {
		return service.removePost(id);
	}

	@PutMapping("/post/{id}")
	public PostDto updatePost(@PathVariable String id, @RequestBody NewPostDto postUpdateDto) {
		return service.updatePost(postUpdateDto, id);
	}

	@PutMapping("/post/{id}/like")
	public void addLike(@PathVariable String id) {
		service.addLike(id);
	}

	@PutMapping("/post/{id}/comment/{author}")
	public PostDto addComment(@PathVariable String id, @PathVariable String author,
			@RequestBody NewCommentDto newCommentDto) {
		return service.addComment(id, author, newCommentDto);
	}

	@GetMapping("/posts/author/{author}")
	public Iterable<PostDto> getPostByAuthor(@PathVariable String author) {
		return service.findPostByAutor(author);
	}

	@PostMapping("/posts/tags")
	public Iterable<PostDto> getPostByTags(@RequestBody List<String> tags) {
		return service.findPostsByTags(tags);
	}

	@PostMapping("/posts/period")
	public Iterable<PostDto> getPostsByPeriod(@RequestBody DateDto date) {
		return service.findPostsByPeriod(date);
	}

}
