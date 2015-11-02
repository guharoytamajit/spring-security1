package demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Employee;
import demo.service.IEmployeeSvc;

@RestController
@RequestMapping(value = "/api")
public class EmployeeRestController {
	@Autowired
	IEmployeeSvc employeeSvc;

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public Employee save(@RequestBody Employee employee) {
		return employeeSvc.save(employee);
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public Employee update(@RequestBody Employee employee) {
		return employeeSvc.update(employee);
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	public Iterable<Employee> findAll() {
		return employeeSvc.findByAll();
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
	public Employee findById(@PathVariable Integer id) {
		return employeeSvc.findOne(id);
	}

	@RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		employeeSvc.delete(id);
	}
}
