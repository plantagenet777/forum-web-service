package telran.b7a.forum.dto;

public class SuccessResponseDto {
	String id;
	String title;
	String content;
	String author;
	String dateCreated;
	String[] tags;
	Integer likes;
	CommentDto[] comments;

}
