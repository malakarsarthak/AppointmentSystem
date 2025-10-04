package com.it.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PWDModel 
{
   @NotNull(message = "Old Password Not Null !")
   @NotEmpty(message = "Old Password Not Empty !")
   private String oldPwd;
   
   @NotNull(message = "New Password Not Null !")
   @NotEmpty(message = "New Password Not Empty !")
   private String newPwd;
}
