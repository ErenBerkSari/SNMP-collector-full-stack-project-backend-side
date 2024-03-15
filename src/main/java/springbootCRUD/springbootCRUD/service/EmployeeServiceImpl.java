package springbootCRUD.springbootCRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import springbootCRUD.springbootCRUD.dao.EmployeeDAO;
import springbootCRUD.springbootCRUD.model.Employee;
import springbootCRUD.springbootCRUD.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Transactional//897979879798978978
	@Override
	public List<Employee> get() {
		return employeeDAO.get();
	}
	
	@Transactional
	@Override
	public Employee get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);	}
	}


