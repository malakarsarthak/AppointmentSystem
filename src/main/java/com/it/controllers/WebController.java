package com.it.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.Doctor;
import com.it.entities.Patient;
import com.it.entities.User;
import com.it.models.AppointmentModel;
import com.it.models.LoginModel;
import com.it.models.LoginResponse;
import com.it.service.AppointmentService;
import com.it.service.DoctorService;
import com.it.service.UserService;
import com.it.utilis.ApiResponse;
import com.it.utilis.JWTUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hospital")
public class WebController 
{
   @Autowired
   private UserService userService;
   
   @Autowired
   private DoctorService docService;
   
   @Autowired
   private AppointmentService appointmentService;
   
   @PostMapping("/login")
   public ApiResponse login(@RequestBody @Valid LoginModel model)
   {
	   User user = userService.loginCheck(model);
	   if(user == null)
		   return new ApiResponse(false,"Ivalid User, Wrong Mobile No and Password !");
	   else
	   {
		   String token = JWTUtils.createToken(user.getUserId());
		   String role = user.getRole();
		   return new ApiResponse(true,"Login Success",new LoginResponse(token, role));
	   }
   }
  
   @GetMapping("/listdocs")
   public ApiResponse listDoctor()
   {
	   List<Doctor> list = docService.list();
	   return new ApiResponse(true,"Doctor Records!",list);
   }
   
   @RequestMapping("/authfailed")
   public ApiResponse authfailed()
   {
	   return new ApiResponse(false,"Unauthorized Request , Not Allowed !"); 
   }
   
   @GetMapping("/findpatient/{mobile}")
   public ApiResponse findPatient(@PathVariable(value="mobile") String mobile)
   {
	   User user = userService.findByMobile(mobile);
	   if(user==null || ! user.getRole().equals("ROLE_PATIENT"))
	   {
		   return new ApiResponse(false,"Patient Not Found");
	   }
	   else
	   {
		   Patient pat = (Patient) user;
		   return new ApiResponse(true,"Patient Found",pat);
	   }
   }
   
   @PostMapping("/bookapt")
   public ApiResponse bookAppointment(@RequestBody AppointmentModel model)
   {
	   if(model.getPinfo()==null && model.getPid()==null)
	   {
		   return new ApiResponse(false,"Patient Info Not Supplied");
	   }
	   else
	   {
		   Doctor doc = docService.getById(model.getDoctorId());
		   if(doc == null)
		   {
			   return new ApiResponse(false,"Doctor Not Found");
		   }
		   else
		   {
			   if(model.getPinfo()!=null)
			   {
				   Patient pat = userService.savePatient(model.getPinfo());
                   appointmentService.saveRequest(pat, doc, model.getDate());
                   return new ApiResponse(true, "Appointment Request send successfully.",null);
			   } 
			   else
			   {
				   Patient pat = (Patient) userService.getById(model.getPid());
				   appointmentService.saveRequest(pat, doc, model.getDate());
				   return new ApiResponse(true,"Appointment Request send successfully",null);
			   }
		   }
	   }
   }
}  
