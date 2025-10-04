package com.it.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentModel 
{
   @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate date;
   private Integer doctorId;
   
   private PatientInfo pinfo;
   
   private Integer pid;
   
   @AllArgsConstructor
   @NoArgsConstructor
   @Data
   public static class PatientInfo
   {
	   private String name;
	   private String mobile;
	   private String gender;
	   private String email;
	   @JsonFormat(pattern = "dd/MM/yyyy")
	   private LocalDate dob;
   }
}
