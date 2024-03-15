package springbootCRUD.springbootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springbootCRUD.springbootCRUD.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>
{

}

