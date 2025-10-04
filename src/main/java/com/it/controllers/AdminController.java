package com.it.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.Receptionist;
import com.it.models.DoctorModel;
import com.it.models.RecepionistModel;
import com.it.service.DoctorService;
import com.it.service.UserService;
import com.it.utilis.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/admin")
public class AdminController 
{
	@Autowired
	private DoctorService docService;

	@Autowired 
	private UserService  userService;

	@PostMapping("/savedoc")
	public ApiResponse saveDoctor(@Valid @RequestBody DoctorModel model)
	{
		boolean status = docService.saveDoctor(model);
		if(status)
		{
			return new ApiResponse(true,"Doctor Saved",null);
		}
		else
		{
			return new ApiResponse(false,"Doctor Not saved !");   
		}
	}
	
	@PostMapping("/saverecp")
	   public ApiResponse save(@RequestBody @Valid RecepionistModel model)
	   {
//		   System.out.println(model);
		   userService.saveRecep(model);
		   return new ApiResponse(true,"Receptionist saved",null);
	   }
	
	@GetMapping("/listrecep")
	public ApiResponse list()
	{
		List<Receptionist> list  = userService.listAllReceptionists();
		return new ApiResponse(true,"Reception record!",list);
	}
}
