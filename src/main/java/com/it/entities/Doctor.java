package com.it.entities;

import com.it.models.DoctorModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Doctor 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="doc_id")
  private Integer docId;
  
  @Column(name="Name",nullable = false)
  private String name;
  
  @Column(name="Mobile",nullable = false,unique = true)
  private String mobile;
  
  @Column(name="Specialization",nullable = false)
  private String specialization;
  
  @Column(name="Timing",nullable = false)
  private  String time;
  
  @Column(name="Consulting Fees",nullable = false)
  private Float ConsultingFees;
  
  public Doctor(DoctorModel model)
  {
	  this.name=model.getName();
	  this.mobile=model.getMobile();
	  this.specialization=model.getSpecs();
	  this.time=model.getTiming();
	  this.ConsultingFees=model.getFees();
  }
}
