package SpringbootCrudRestfulwebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import SpringbootCrudRestfulwebservice.exception.ResourceNotFoundException;
import SpringbootCrudRestfulwebservice.model.Employee;
import SpringbootCrudRestfulwebservice.repository.EmployeeRopository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRopository employeeRepo;
	
//	create get all employee end points
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
//	create employee
	
	@PostMapping("/employees")
	public Employee createEmployees( @RequestBody Employee employee){
		return employeeRepo.save(employee);
	}
	
//	get employees by i
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException
	{
		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(()-> new ResourceNotFoundException("not found with this id "+ employeeId));
		return ResponseEntity.ok().body(employee);
	}
	
//	update employee
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long employeeId,
			@RequestBody Employee employeeDetails)throws ResourceNotFoundException
	{
		Employee employee = employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("cannot get this id"+ employeeId));
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employeeRepo.save(employee);
		return ResponseEntity.ok().body(employee);
	}

//	delete employee
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id"  ) long employeeId) throws ResourceNotFoundException 
	{
		employeeRepo.findById(employeeId)
		.orElseThrow(()-> new ResourceNotFoundException("not found, can't delete " + employeeId));
		employeeRepo.deleteById(employeeId);
		return ResponseEntity.ok().build();
	}
	
}
