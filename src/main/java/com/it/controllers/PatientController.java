package com.it.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.User;
import com.it.service.AppointmentService;
import com.it.utilis.ApiResponse;

@RestController
@RequestMapping("/auth/patient")
public class PatientController 
{
   @Autowired
   private AppointmentService appService;
   
   @GetMapping("/myRequests")
   public ApiResponse listRequests()
   {
	   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   return new ApiResponse(true,"Rquests List",appService.patientAppRequest(user));
   }
   
   @GetMapping("/myAppointments")
   public ApiResponse listAppointments()
   {
	   User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   return new ApiResponse(true,"Appointment List", appService.listPatientAppointment(user));
   }
}
