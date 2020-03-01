package com.orangeman.example.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.orangeman.example.batch.model.Employee;

public class EmployeeKafkaSender implements ItemWriter<Employee> {

	@Autowired
	private Sender sender;
	
	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		// TODO Auto-generated method stub
		employees.stream().forEach(employee -> {
			sender.send(employee);
		});
		System.out.println("Message sent to kafka");
	}
		

}
