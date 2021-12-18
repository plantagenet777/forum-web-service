package telran.b7a.forum.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DateDto {
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
	LocalDate dateFrom;
	@JsonFormat(pattern = "[yyyy-MM-dd][dd-MM-yyyy]")
    LocalDate dateTo;

}
