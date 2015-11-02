package demo.repo;

import org.springframework.data.repository.CrudRepository;

import demo.entity.Vehicle;

public interface VehicleDao extends CrudRepository<Vehicle, String> {

}
