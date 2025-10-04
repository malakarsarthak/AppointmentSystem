package com.it.entities;

import java.time.LocalDate;

import com.it.models.RecepionistModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receptionist extends User
{
	@Column(name="Name",nullable = false)
    private String name;
	
	@Column(name="Joining_Date",nullable = false)
	private LocalDate joiningDate;
	
	@Column(name="Leaving_Date",nullable = true)
	private LocalDate leavingDate;

	@Column(name="Email",nullable = false)
	private String email;
	
    public Receptionist(RecepionistModel model,String pwd)
    {
    	this.name = model.getName();
    	this.joiningDate=model.getJoiningDate();
    	this.setMobile(model.getMobile());
    	this.setEmail(model.getEmail());
    	this.setPassword(pwd);
    	this.setActivestatus(true);
    	this.setRole("ROLE_RECEP");
    }

}

