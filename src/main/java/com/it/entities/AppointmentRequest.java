package com.it.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppointmentRequest 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="Request_Id")
   private Integer requestId;
   
   @ManyToOne
   @JoinColumn(name="patient",nullable = false)
   private Patient patient;
   
   @Column(name="Appointment_Date",nullable = false)
   private LocalDate appointmentDate;
   
   @Column(name="Request_Date",nullable = false)
   private LocalDate requestDate;
   
   @ManyToOne
   @JoinColumn(name="doctor",nullable = false)
   private Doctor doctor;
   
   @Column(name="Active_Status",nullable = false)
   private boolean activestatus;
   
   @Column(name="Status",nullable = false)
   private String status;

   @Column(name="Cancel_Description",nullable = true)
   private String cancelDescription ;

}
