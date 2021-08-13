package com.springdatarest.employeeapi.repositories;

import com.springdatarest.employeeapi.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepositoryInterface extends CrudRepository<Employee, Long> {
}
