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
import com.orangeman.example.batch.processor.EmployeeProcessor;
import com.orangeman.example.batch.writer.EmployeeKafkaSender;


@Configuration
public class Demo {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private EmployeeProcessor employeeProcessor;

	@Autowired
	public Demo(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			EmployeeProcessor employeeProcessor) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.employeeProcessor = employeeProcessor;
	}
	
	@Qualifier(value = "demo")
	@Bean
	public Job demoJob() throws Exception {
		return this.jobBuilderFactory.get("demo")
				.start(step1Demo())
				.build();
	}

	@Bean
	public Step step1Demo() throws Exception {
		// TODO Auto-generated method stub
		return this.stepBuilderFactory.get("step1")
				.<EmployeeDTO, Employee>chunk(1)
                .reader(employeeReader())
                .processor(employeeProcessor)
                .writer(employeeKafkaSender())
                .build();
	}

	@Bean
    @StepScope
    Resource inputFileResource(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }

	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDTO> employeeReader() throws Exception {
		// TODO Auto-generated method stub
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
	
	@Bean
	public EmployeeKafkaSender employeeKafkaSender() {
		// TODO Auto-generated method stub
		return new EmployeeKafkaSender();
	}

}
