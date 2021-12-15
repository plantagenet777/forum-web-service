package telran.b7a.forum.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.forum.dao.PostMongoRepository;
import telran.b7a.forum.dto.PostDto;
import telran.b7a.forum.dto.NewPostDto;
import telran.b7a.forum.dto.DateDto;
import telran.b7a.forum.dto.NewCommentDto;
import telran.b7a.forum.dto.exceptions.PostNotFoundException;
import telran.b7a.forum.model.Comment;
import telran.b7a.forum.model.Post;

@Service
public class ForumServiceImpl implements ForumService {

	PostMongoRepository postRepository;
	ModelMapper modelMapper;

	@Autowired
	public ForumServiceImpl(PostMongoRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto addNewPost(NewPostDto newPostDto, String author) {
		Post post = modelMapper.map(newPostDto, Post.class);
		post.setAuthor(author);
		post.setDateCreated(LocalDateTime.now());
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto getPost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto removePost(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		postRepository.deleteById(id);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(NewPostDto postUpdateDto, String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		String content = postUpdateDto.getContent();
		if (content != null) {
			post.setContent(postUpdateDto.getContent());
		};
		String title = postUpdateDto.getTitle();
		if (title != null) {
			post.setTitle(postUpdateDto.getTitle());
		};
		Set<String> tags = postUpdateDto.getTags();
		if (tags != null) {
			tags.forEach(post::addTag);
		};
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public void addLike(String id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		post.addLike();
		postRepository.save(post);
	}

	@Override
	public PostDto addComment(String id, String author, NewCommentDto newCommentDto) {
		Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		Comment comment = modelMapper.map(newCommentDto, Comment.class);
		comment.setUser(author);
		comment.setDateCreated(LocalDateTime.now());
		post.addComment(comment);
		postRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public Iterable<PostDto> findPostByAutor(String author) {
		return postRepository.findPostsByAuthor(author)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByTags(List<String> tags) {
		return postRepository.findPostsByTags(tags)
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> findPostsByPeriod(DateDto date) {
		return postRepository.findByDateCreatedBetween(convertDateTimeFrom(date), convertDateTimeTo(date))
				.map(p -> modelMapper.map(p, PostDto.class))
				.collect(Collectors.toList());
	}

	private LocalDateTime convertDateTimeFrom(DateDto date) {
		return date.getDateFrom().atStartOfDay();
	}

	private LocalDateTime convertDateTimeTo(DateDto date) {
		return date.getDateTo().atStartOfDay();
	}

}
