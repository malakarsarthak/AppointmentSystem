package com.it.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecepionistModel 
{
	@NotNull(message = "Name Can't Be Null")
	@NotEmpty(message = "Name Can't Be Empty")
	private String name;
	
	@NotNull(message = "JoiningDate Can't Be Null")
	@JsonFormat(pattern = "dd/MM/yyyy") 
	private LocalDate joiningDate;
	
	@NotNull(message = "Mobile No Can't Be Null")
	@NotNull(message = "Mobile No Can't Be Empty")
	private String mobile;
	
	@NotNull(message = "Email No Can't Be Null")
	@NotNull(message = "Email No Can't Be Empty")
	private String Email;
}
