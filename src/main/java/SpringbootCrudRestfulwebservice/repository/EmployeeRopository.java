package SpringbootCrudRestfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringbootCrudRestfulwebservice.model.Employee;

@Repository
public interface EmployeeRopository extends JpaRepository<Employee,Long>{

}
