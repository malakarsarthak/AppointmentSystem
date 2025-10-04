package com.it.repositeries;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.it.entities.AppointmentRequest;
import com.it.entities.Patient;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentRequest,Integer>
{
	@Query("SELECT R FROM AppointmentRequest as R WHERE appointmentDate>=?1and activestatus=true ORDER BY appointmentDate")
    List<AppointmentRequest> findRequests(LocalDate appointmentDate);
	
	@Query("Select R from AppointmentRequest as R Where appointmentDate>=?1and patient>=?2 order By appointmentDate")
	List<AppointmentRequest> findPatient(LocalDate appointmentDate,Patient patient);
}
