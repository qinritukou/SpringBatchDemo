package com.orangeman.example.batch.processor;

import java.util.Random;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.orangeman.example.batch.dto.EmployeeDTO;
import com.orangeman.example.batch.model.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee> {

	@Override
	public Employee process(EmployeeDTO employeeDTO) throws Exception {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId() + new Random().nextInt(10000000));
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAge(employeeDTO.getAge());
        //System.out.println("inside processor " + employee.toString());
        return employee;
    }

}
