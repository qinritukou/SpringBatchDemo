package com.orangeman.example.batch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.orangeman.example.batch.dto.EmployeeDTO;
import com.orangeman.example.batch.model.Employee;

@Component
public class EmployeeCSVToDBProcessor implements ItemProcessor<EmployeeDTO, Employee> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeDTO.class);
	
	@Override
    public Employee process(EmployeeDTO employeeDTO) throws Exception {
		logger.info("Inside process method. Employee = {}", employeeDTO.toString());
		if (!isValid(employeeDTO)) {
			return null;
		}
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAge(employeeDTO.getAge());
        return employee;
    }
	
	boolean isValid(EmployeeDTO employeeDTO) {
		return (employeeDTO.getFirstName().startsWith("AAA")) ? false : true;
	}
	
}
