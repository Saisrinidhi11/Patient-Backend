package com.mindtree.patient.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.patient.exception.ResourceNotFoundException;
import com.mindtree.patient.model.Patient;
import com.mindtree.patient.repository.PatientRepository;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/v1/")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient) {
		return patientRepository.save(patient);
	}
	//get patient id rest API
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
		Patient patient=patientRepository.findById(id).
		orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:"+id));
		return ResponseEntity.ok(patient);
	}	
	// update patient rest API
		@PutMapping("/patients/{id}")
		public ResponseEntity<Patient> 
		updatePatient(@PathVariable Long id,@RequestBody Patient patientDetails){
			
			Patient patient=patientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:"+id));
			patient.setname(patientDetails.getname());
			patient.setvisitedDoctor(patientDetails.getvisitedDoctor());
			patient.setdateOfVisit(patientDetails.getdateOfVisit());
			
			Patient updatedPatient=patientRepository.save(patient);
			return ResponseEntity.ok(updatedPatient);
		}	
	
		//delete patient rest API
		@DeleteMapping("/patients/{id}")
		public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id){
			Patient patient=patientRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Patient not exist with id:"+id));
			
			patientRepository.delete(patient);
			Map<String,Boolean> response=new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}	
}
