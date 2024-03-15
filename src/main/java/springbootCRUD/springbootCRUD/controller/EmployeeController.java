package springbootCRUD.springbootCRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springbootCRUD.springbootCRUD.model.Employee;
import springbootCRUD.springbootCRUD.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	

	@Autowired
	private EmployeeService employeeService;
	
	@CrossOrigin(origins = "*")
	@GetMapping(value="/employee")
	public List<Employee> get()
	{
		return employeeService.get();
	}
	
	@PostMapping(value={"/employee-post"},consumes={"application/json"}) 
	//@PostMapping(value={"/employee-post"}) 
	public Employee addEmployee(@RequestBody Employee employee)
	{
		return employeeService.addEmployee(employee);
	}
	
	
	
}
