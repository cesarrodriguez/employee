package br.com.luizalabs.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luizalabs.entity.Employee;

/**
 * This interface is to used by Spring Data to create metadatas queries.
 * 
 * Please see the link class for true identity
 * 
 * @author Black Widow
 * 
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
