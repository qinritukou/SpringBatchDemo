package com.orangeman.example.batch.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orangeman.example.batch.model.Employee;
import com.orangeman.example.batch.repo.EmployeeRepo;

@Component
public class EmployeeDBWriter implements ItemWriter<Employee> {

	private static Logger logger = LoggerFactory.getLogger(EmployeeDBWriter.class);
	
	private EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeDBWriter(EmployeeRepo employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		// TODO Auto-generated method stub
		employeeRepo.saveAll(employees);
		logger.info("{} employees saved in database", employees.size());
	}

}
