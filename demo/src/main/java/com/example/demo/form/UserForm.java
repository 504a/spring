package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserForm {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 1, max = 10)
	private String name;

	private boolean gender = true;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date birth;

}
