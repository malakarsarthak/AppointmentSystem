package com.it.controllers;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it.entities.AppointmentModule;
import com.it.entities.AppointmentRequest;
import com.it.service.AppointmentService;
import com.it.utilis.ApiResponse;


@RestController
@RequestMapping("/auth/recp")
public class ReceptionistController 
{
	@Autowired
    private AppointmentService appService;
	
	@GetMapping("/listRequests")
	public ApiResponse listRequest()
	{
		 return new ApiResponse(true,"Request List",appService.listAppRequest());
	}
	
	@PostMapping("/approveRequest/{reqid}")
	public ApiResponse approveRequest(@PathVariable(value="reqid") Integer reqid)
	{
		AppointmentRequest req = appService.getRequestById(reqid);
		if(req == null || req.isActivestatus()==false)
		{
			return new ApiResponse(false,"Request Not Found or Request already cancelled");
		}
		else
		{
			AppointmentModule app = new AppointmentModule();
			app.setPatient(req.getPatient());
			app.setAppointmentDate(req.getAppointmentDate());
			app.setAppointmentTime(LocalTime.of(14,25,0));
			app.setDoctor(req.getDoctor());
			app.setActivestatus(true);
			app.setType("online");
			app.setRequest(req);
			app.setStatus("approve");
			appService.save(app);
			
			req.setActivestatus(false);
			req.setStatus("approve");
			return new ApiResponse(true,"Appointment Done",null);
		}
	}
	
	@PatchMapping("/cancelRequest/{reqid}")
	public ApiResponse cancelRequest(@PathVariable(value="reqid") Integer reqid)
	{
		AppointmentRequest req = appService.getRequestById(reqid);
		if(req==null)
		{
			return new ApiResponse(false,"Request Not Found");
		}
		else
		{
			req.setActivestatus(false);
			req.setStatus("cancel");
			appService.updateRequest(req);
			return new ApiResponse(true,"Request Cancelled",null);
		}
	}
	
	@GetMapping("/listAppointment")
	public ApiResponse listAppointment()
	{
		return new ApiResponse(true,"Appointment List",appService.listAppointment());
	}
}
