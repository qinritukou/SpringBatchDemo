package com.orangeman.example.batch.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class BaseConfig {

	public JobRepository jobRepository;
	
	@Autowired
	public BaseConfig(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
	@Bean
	public JobLauncher simpleJobLauncher() {
		SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
		simpleJobLauncher.setJobRepository(jobRepository);
		return simpleJobLauncher;
	}
	
}
