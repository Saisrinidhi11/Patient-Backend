package com.mindtree.patient.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Entity
@Table(name= "patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="visitedDoctor")
	private String visitedDoctor;
	
	@Column(name="dateOfVisit")
	private LocalDate dateOfVisit;
	
	public Patient() {
		
	}
	
	public Patient(String name,String visitedDoctor,LocalDate dateOfVisit) {
		super();
		this.name=name;
		this.visitedDoctor=visitedDoctor;
		this.dateOfVisit=dateOfVisit;
		}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name=name;
	}
	public String getvisitedDoctor() {
		return visitedDoctor;
	}
	public void setvisitedDoctor(String visitedDoctor) {
		this.visitedDoctor=visitedDoctor;
	}
	public LocalDate getdateOfVisit() {
		return dateOfVisit;
	}
	public void setdateOfVisit(LocalDate dateOfVisit) {
		this.dateOfVisit=dateOfVisit;
	}
}
