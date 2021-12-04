package ca.sheridancollege.jungjuyo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.jungjuyo.beans.Student;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String goRoot(Model model, RestTemplate restTemplate) {
		
	//	model.addAttribute("Students", stuRepo.getStudents());
		// -------------> instead, connect to web service
		
		ResponseEntity<Student[]> responseEntity = 
				restTemplate
				.getForEntity("http://localhost:8080/students", Student[].class);
		
		model.addAttribute("students", responseEntity.getBody());
		return "index.html";
	}
	
	@RequestMapping(value="/getStudent/{id}", produces="application/json")
	@ResponseBody
	public Map<String, Object> getStudent(@PathVariable int id, 
			RestTemplate restTemplate) {
		
		
		// Get a single student based on the id
		ResponseEntity<Student> responseEntity = 
				restTemplate
				.getForEntity("http://localhost:8080/students/" + id, Student.class);
		
		// Stores the single student as a  record in a Map
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("student", responseEntity.getBody());
		
		// Return the data in this method
		return data;
	}
	

}
