package com.it.models;

import org.hibernate.validator.constraints.Length;

import com.it.vaildators.ValidSpecs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorModel 
{
   @NotNull(message = "Doctor Name Not Null")
   @NotEmpty(message = "Doctor Name Not Empty")
   private String name;
   
   @NotNull(message = "Doctor Mobile Not Null")
   @NotEmpty(message = "Doctor Mobile Not Empty")
   @Length(min=10,max=10,message = "Wrong Mobile Number")
   private String mobile;
   
   @NotNull(message = "Doctor Specialization Can't be Null")
   @NotEmpty(message = "Doctor Specialization Can't be Empty")
   @ValidSpecs
   private String specs;
   
   @NotNull(message = "Doctor Timing Can't be Null")
   @NotEmpty(message = "Doctor Timing Can't be Empty")
   private String timing;
   
   @NotNull(message = "Doctor Fees Can't be Null")
   @Min(value=200, message = "Doctor Fees Incorrect.")
   private Float fees;  
}
