package com.it.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppointmentModule 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="Appointment_Id")
  private Integer appointment_id;
  
  @ManyToOne
  @JoinColumn(name="patient",nullable = false)
  private Patient patient;
  
  @Column(name="Appointment_Date",nullable = false)
  private LocalDate appointmentDate;

  @Column(name="Reject_Date",nullable = true)
  private LocalDate rejectDate;
  
  @Column(name="Time",nullable = false)
  private LocalTime appointmentTime;
  
  @ManyToOne
  @JoinColumn(name="doctor",nullable = false)
  private Doctor doctor;
  
  @Column(name = "type",nullable = false)
  private String type;
  
  @OneToOne
  @JoinColumn(name="request",nullable = true)
  private AppointmentRequest request;
  
  @Column(name="Active_Status",nullable = false)
  private boolean activestatus;
  
  @Column(name="Status",nullable = false)
  private String status;
  
  @Column(name="Reject_Description",nullable = true)
  private String rejectDescription;
}
