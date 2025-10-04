package com.it.repositeries;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.entities.AppointmentModule;
import com.it.entities.Patient;

@Repository
public interface AppRepo extends JpaRepository<AppointmentModule, Integer>
{
	@Query("Select R from AppointmentModule as R Where appointmentDate>=?1 and activestatus=true Order By appointmentDate")
	List<AppointmentModule> findAppointments(LocalDate appointmentDate);  
	
	@Query("Select R from AppointmentModule as R Where appointmentDate>=?1 and patient=?2 Order By appointmentDate")
	List<AppointmentModule> findPatientAppointments(LocalDate appointmentDate,Patient patient); 
}
