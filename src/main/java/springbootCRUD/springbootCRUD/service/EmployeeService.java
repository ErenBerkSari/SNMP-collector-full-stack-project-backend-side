package springbootCRUD.springbootCRUD.service;

import java.util.List;


import springbootCRUD.springbootCRUD.model.Employee;

public interface EmployeeService {
	Employee addEmployee(Employee employee);

	List<Employee> get();
	
	Employee get(int id);
	
	void save(Employee employee);
	
	void delete(int id);
}
