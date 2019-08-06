package br.com.luizalabs.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizalabs.employee.repository.EmployeeRepository;
import br.com.luizalabs.entity.Employee;
import br.com.luizalabs.entity.EmployeeRestParameter;

@RestController
@RequestMapping(value = "/employee", produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	private EmployeeRepository repository;

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeRestParameter params) throws URISyntaxException {
		Employee employee = repository.save(Employee.builder().name(params.getName()).email(params.getEmail())
				.department(params.getDepartment()).build());
		return ResponseEntity.created(new URI("/employee/" + employee.getId())).body( employee);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
		Optional<Employee> optional = repository.findById(id);
		if (!optional.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		repository.deleteById(id);
		return ResponseEntity.ok("");
	}


	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> list = repository.findAll();
		if (list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(list);
	}
}