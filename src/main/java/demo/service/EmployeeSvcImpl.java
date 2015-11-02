package demo.service;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.entity.Employee;
import demo.exception.EmployeeAlreadyExists;
import demo.repo.EmployeeDao;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeSvcImpl implements IEmployeeSvc {
	@Autowired
	EmployeeDao employeeDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@PreAuthorize("hasAnyRole('admin','user')")
	public Employee save(Employee employee) {
		if (employeeDao.exists(employee.getEid())) {
			new EmployeeAlreadyExists(employee);
		}
		return employeeDao.save(employee);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	// @PreAuthorize("hasRole('admin') or hasRole('user')")
	@RolesAllowed({ "admin", "user" })
	// jsr250 equivalent of @PreAuthorize("hasAnyRole('admin','user')")
	public Employee update(Employee employee) {
		return employeeDao.save(employee);
	}

	@PreAuthorize("isAuthenticated()")
	public Employee findOne(Integer eid) {
		return employeeDao.findOne(eid);

	}

	@PreAuthorize("isAuthenticated()")
	public Iterable<Employee> findByAll() {
		return employeeDao.findAll();

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@PreAuthorize("hasRole('admin') and #id != 0")
	// even admin cannot delete employee with id 0.
	public void delete(Integer id) {
		employeeDao.delete(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@PreAuthorize("hasRole('admin')")
	public void deleteAll() {
		employeeDao.deleteAll();

	}

}
