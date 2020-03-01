package com.orangeman.example.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.orangeman.example.batch.dto.EmployeeDTO;
import com.orangeman.example.batch.model.Employee;

@Component
public class EmployeeDBToCSVProcessor implements ItemProcessor<Employee, EmployeeDTO> {

	@Override
	public EmployeeDTO process(Employee employee) throws Exception {
		// TODO Auto-generated method stub
		EmployeeDTO employeeDTO = new EmployeeDTO(); 
		employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAge(employee.getAge()+10);
        System.out.println("inside processor " + employee.toString());
        return employeeDTO;
   }

}
