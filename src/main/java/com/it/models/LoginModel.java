package com.it.models;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginModel
{
   @NotEmpty(message = "Mobile Can't be Empty")
   @NotNull(message = "Mobile Can't be Null")
   @Length(min = 10 ,max = 10,message = "Wrong Mobile No")
   private String mobile;
   
   @NotEmpty(message = "Password Can't be Empty")
   @NotNull(message = "Password Can't be Null")
   private String pwd;
}
