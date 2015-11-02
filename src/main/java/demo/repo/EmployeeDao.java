package demo.repo;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Employee;

public interface EmployeeDao extends CrudRepository<Employee, Integer> {

}
