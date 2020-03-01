package com.orangeman.example.batch.job;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.orangeman.example.batch.dto.EmployeeDTO;
import com.orangeman.example.batch.mapper.EmployeeFileRowMapper;
import com.orangeman.example.batch.model.Employee;
import com.orangeman.example.batch.processor.EmployeeCSVToDBProcessor;
import com.orangeman.example.batch.writer.EmployeeDBWriter;

@Configuration
public class Demo1 {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private EmployeeCSVToDBProcessor employeeProcessor;
	private EmployeeDBWriter employeeDbWriter;
	
	
	
	@Autowired
	public Demo1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			EmployeeCSVToDBProcessor employeeProcessor, EmployeeDBWriter employeeDBWriter) {
		super();
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.employeeProcessor = employeeProcessor;
		this.employeeDbWriter = employeeDBWriter;
	}
	
	@Qualifier(value = "CSV2DB")
	@Bean
	public Job demo1Job() {
		return this.jobBuilderFactory.get("csv2db")
				.start(step1Demo())
				.build();
	}
	
	@Bean
	public Step step1Demo() {
		return this.stepBuilderFactory.get("step1")
				.<EmployeeDTO, Employee>chunk(5)
				.reader(employeeReader())
				.processor(employeeProcessor)
				.writer(employeeDbWriter)
				.build();
	}

	@Bean
	@StepScope
	public Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) {
		// TODO Auto-generated method stub
		return new ClassPathResource(fileName);
	}

	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDTO> employeeReader()  {
		FlatFileItemReader<EmployeeDTO> reader = new FlatFileItemReader<>();
		reader.setResource(inputFileResource(null));
		reader.setLineMapper(new DefaultLineMapper<EmployeeDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("employeeId", "firstName", "lastName", "email", "age");
                setDelimiter(",");
            }});
            setFieldSetMapper(new EmployeeFileRowMapper());			
		}});
		
		return reader;
	}
	

	
}
