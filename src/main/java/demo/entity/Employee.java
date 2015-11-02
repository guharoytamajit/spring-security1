package demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Employee {
	String name;
	@Id
	Integer eid;
	String location;
	@OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	List<Vehicle> vehicleList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public Employee(String name, int eid) {
		super();
		this.name = name;
		this.eid = eid;
	}

	public Employee() {
		super();

	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", eid=" + eid + "]";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
