# ConsumingWebServiceExample
Created an application that consumes a RESTful web service.
- Used StudentList RESTful web service project.

## RestTemplate class
Spring provides a convenient template class called `RestTemplate`. `RestTemplate` makes interacting with most RESTful services a one-line interaction. Even, it can bind that data to custom domain types.

```java
@Controller
public class HomeController {
	
	@GetMapping("/")
	public String goRoot(Model model, RestTemplate restTemplate) {
		
	  //model.addAttribute("Students", stuRepo.getStudents());
		// -------------> instead, connect to web service
		
		ResponseEntity<Student[]> responseEntity = 
				restTemplate
				.getForEntity("http://localhost:8080/students", Student[].class);
		
		model.addAttribute("students", responseEntity.getBody());
		return "index.html";
	}
}
```
Consume the get student by id
```java
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
```

### application.properties set up to test out
To test out Consuming Web Service, should run both RESTful Web Service and Consuming Web Service.
RESTful Web Service run on `server.port=8080`
Consuming Web Service run on `server.port=8085` and then, go to "http://localhost:8085" to test out.
