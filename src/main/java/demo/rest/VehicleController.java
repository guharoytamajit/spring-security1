package demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.entity.Vehicle;
import demo.service.IVehicleSvc;

@RestController
public class VehicleController {
	@Autowired
	IVehicleSvc vehicleSvc;

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	public Vehicle save(@RequestBody Vehicle vehicle) {
		return vehicleSvc.save(vehicle);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.PUT)
	public Vehicle update(@RequestBody Vehicle vehicle) {
		return vehicleSvc.update(vehicle);
	}

	@RequestMapping(value = "/vehicle", method = RequestMethod.GET)
	public Iterable<Vehicle> findAll() {
		return vehicleSvc.findByAll();
	}

	@RequestMapping(value = "/vehicle/{number}", method = RequestMethod.GET)
	public Vehicle findById(@PathVariable String number) {
		return vehicleSvc.findOne(number);
	}

	@RequestMapping(value = "/vehicle/{number}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String number) {
		vehicleSvc.delete(number);
	}
}
