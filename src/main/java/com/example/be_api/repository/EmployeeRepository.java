package com.example.be_api.repository;

import com.example.be_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainingOrEmailContaining(String keyword, String keyword1);
}
