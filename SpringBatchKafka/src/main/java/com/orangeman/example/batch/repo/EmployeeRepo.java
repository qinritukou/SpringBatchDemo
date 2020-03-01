package com.orangeman.example.batch.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orangeman.example.batch.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
}