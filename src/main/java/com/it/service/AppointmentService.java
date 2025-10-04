package com.it.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.entities.AppointmentModule;
import com.it.entities.AppointmentRequest;
import com.it.entities.Doctor;
import com.it.entities.Patient;
import com.it.entities.User;
import com.it.repositeries.AppRepo;
import com.it.repositeries.AppointmentRepo;
import com.it.repositeries.UserRepo;


@Service
public class AppointmentService 
{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailServices mailServices;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private AppRepo appRepo;
    
    public void saveRequest(Patient pat, Doctor doc, LocalDate date) 
    {
       AppointmentRequest req = new AppointmentRequest();
       req.setPatient(pat);
       req.setDoctor(doc);
       req.setAppointmentDate(date);
       req.setRequestDate(LocalDate.now());
       req.setActivestatus(true);
       req.setStatus("Pending");
       
       appointmentRepo.save(req); 
    }
    
    public List<AppointmentRequest> listAppRequest()
    {
    	 return appointmentRepo.findRequests(LocalDate.now());
    }

	public List<AppointmentRequest> patientAppRequest(User user) 
	{
		return appointmentRepo.findPatient(LocalDate.now(),(Patient)user);
    }
	
	public AppointmentRequest getRequestById(Integer id)
	{
		Optional<AppointmentRequest> op = appointmentRepo.findById(id);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	public void save(AppointmentModule app) 
	{
		appRepo.save(app);
	}

	public void updateRequest(AppointmentRequest req) 
	{
		appointmentRepo.save(req);
	}

	public List<AppointmentModule> listAppointment() 
	{
		return appRepo.findAppointments(LocalDate.now());
	}
	
	public List<AppointmentModule> listPatientAppointment(User user)
	{
		return appRepo.findPatientAppointments(LocalDate.now(),(Patient) user);
	}
}
