package com.it.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.it.entities.Patient;
import com.it.entities.Receptionist;
import com.it.entities.User;
import com.it.models.AppointmentModel.PatientInfo;
import com.it.models.LoginModel;
import com.it.models.RecepionistModel;
import com.it.repositeries.DoctorRepo;
import com.it.repositeries.UserRepo;
import com.it.utilis.UtilsFuncs;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@Service
public class UserService implements UserDetailsService
{
   @Autowired 
   private UserRepo userRepo;
   @Autowired
   private MailServices mailServices;
   @Autowired
   private DoctorRepo doctorRepo;
   
   public void saveRecep(RecepionistModel model)
   {
	   try
	   {
		   String pwd = Integer.toString(UtilsFuncs.generatePWD());
		   Receptionist obj = new Receptionist(model,pwd);
		   userRepo.save(obj);  

		   mailServices.userSaveMailSender(model.getName(), model.getEmail(), pwd);
	   } 
	   catch (MessagingException e) 
	   {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
   }
   
   public List<Receptionist> listAllReceptionists()
   {
	   return userRepo.findByRole("ROLE_RECEP");
   }
   
   public User loginCheck(@Valid LoginModel model) 
   {
        Optional<User> op = userRepo.findByMobileAndPassword(model.getMobile(), model.getPwd());
        if(op.isPresent())
        	return op.get();
        else
        	return null;
   }
   
   public void update(User user)
   {
	   userRepo.save(user);
   }
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
   {
	 Optional<User> op = userRepo.findByMobile(username);
	 if(op.isPresent())
		 return op.get();
	 else
		 return null;
   }

   public Patient savePatient(PatientInfo pinfo) 
   {
     String pwd = Integer.toString(UtilsFuncs.generatePWD());
     Patient obj = new Patient(pinfo,pwd);
     obj = userRepo.save(obj);
	 return obj;
   }

   public User getById(Integer id)
   {
	   Optional<User> op = userRepo.findById(id);
	   if(op.isPresent())
	   {
		   return op.get();
	   }
	   else
	   {
		   return null;
	   }
   }
   
   public User findByMobile(String mobile)
   {
	   Optional<User> op = userRepo.findByMobile(mobile);
	   if(op.isPresent())
	   {
		   return op.get();
	   }
	   else
	   {
		   return null;
	   }
   }
}