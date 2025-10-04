package com.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.User;
import com.it.models.PWDModel;
import com.it.service.UserService;
import com.it.utilis.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/user")
public class UserController
{
   @Autowired
   private UserService userService;
   
   @PatchMapping("/changepwd")
   public ApiResponse changePwd(@RequestBody @Valid PWDModel model)
   {
	   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   if(user.getPassword().equals(model.getOldPwd()))
	   {
		   user.setPassword(model.getNewPwd());
		   userService.update(user);
		   return new ApiResponse(true,"Password Change",null);
	   }
	   else
	   {
		   return new ApiResponse(false,"Old password Not Match");
	   }
   }
}
